package com.example.uberapp_tim21.activity.passenger;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.dto.GetUserDTO;
import com.example.uberapp_tim21.activity.dto.LocationDTO;
import com.example.uberapp_tim21.activity.dto.PassengerDTO;
import com.example.uberapp_tim21.activity.dto.RideLocationDTO;
import com.example.uberapp_tim21.activity.dto.SendRideDTO;
import com.example.uberapp_tim21.activity.dto.UserDTO;
import com.example.uberapp_tim21.activity.model.User;
import com.example.uberapp_tim21.activity.service.ServiceUtils;
import com.example.uberapp_tim21.activity.tools.DirectionPointListener;
import com.example.uberapp_tim21.activity.tools.GetPathFromLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerHomeFragment extends Fragment implements LocationListener, OnMapReadyCallback, View.OnFocusChangeListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private LocationManager locationManager;
    private String provider;
    private SupportMapFragment mMapFragment;
    private AlertDialog dialog;
    private Marker home;
    private GoogleMap map;
    EditText startLocation ;
    EditText endLocation;
    Marker startMarker;
    Marker endMarker;
    LatLng startCoordinates;
    LatLng endCoordinates;
    private String TAG = "so47492459";
    Polyline route;
    SendRideDTO ourRide;
    GetUserDTO userDTO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (!hasFocus) {
            switch (view.getId()) {
                case R.id.start_location_passenger:
                    EditText sLocation = ((EditText)getView().findViewById(R.id.start_location_passenger));
                    String sAddress = sLocation.getText().toString();
                    if(sAddress.length() > 5){
                        startCoordinates = getLocationFromAddress(getContext(), sAddress);
                        if(startMarker != null){
                            startMarker.remove();
                        }
                        startMarker = map.addMarker(new MarkerOptions().position(startCoordinates).title("Your route")
                                .snippet("Price: 320din Duration:6min Distance 2.4km"));

                        startMarker.hideInfoWindow();
                        map.moveCamera(CameraUpdateFactory.newLatLng(startCoordinates));
                        createRoute();
                    }

                    break;
                case R.id.end_location_passenger:
                    EditText eLocation = ((EditText)getView().findViewById(R.id.end_location_passenger));
                    String eAddress = eLocation.getText().toString();
                    if (eAddress.length() > 5){
                        endCoordinates = getLocationFromAddress(getContext(), eAddress);
                        if(endMarker != null){
                            endMarker.remove();
                        }
                        endMarker = map.addMarker(new MarkerOptions().position(endCoordinates).title("Nemanja sikelic"));
                        map.moveCamera(CameraUpdateFactory.newLatLng(endCoordinates));
                        createRoute();
                    }

                    break;
            }
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_passenger_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((EditText)getView().findViewById(R.id.start_location_passenger)).setOnFocusChangeListener(this);
        ((EditText)getView().findViewById(R.id.end_location_passenger)).setOnFocusChangeListener(this);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        getView().findViewById(R.id.ride_details).setVisibility(View.GONE);
        Button setRouteBtn = getView().findViewById(R.id.set_route_button);
        setRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().findViewById(R.id.address_input).setVisibility(View.GONE);
                getView().findViewById(R.id.ride_details).setVisibility(View.VISIBLE);

            }
        });
        ImageButton backBtn = getView().findViewById(R.id.back_details);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().findViewById(R.id.address_input).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.ride_details).setVisibility(View.GONE);
            }
        });
        Button findVehicleBtn = getView().findViewById(R.id.find_vehicle_btn);
        findVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRide();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();

        locationManager.removeUpdates(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onResume() {
        super.onResume();

        createMapFragmentAndInflate();

        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean wifi = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Log.i("wwww", String.valueOf(gps));
        Log.i("wqqqq", String.valueOf(wifi));
        if (!gps && !wifi) {
            Log.i("ASD", "ASDresumemap");
            showLocatonDialog();
        } else {
            if (checkLocationPermission()) {
                if (ContextCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {

                    //Request location updates:
                    locationManager.requestLocationUpdates(provider, 2000, 0, this);
                    Toast.makeText(getContext(), "ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                }else if(ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                    //Request location updates:
                    locationManager.requestLocationUpdates(provider, 2000, 0, this);
                    Toast.makeText(getContext(), "ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void showLocatonDialog() {
        if (dialog == null) {
            //dialog = new LocationDialog(getActivity()).prepareDialog();
        } else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        dialog.show();
    }


    private void createMapFragmentAndInflate() {
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getActivity())
                        .setTitle("Allow user location")
                        .setMessage("To continue working we need your locations....Allow now?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{
                                                Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
//        Toast.makeText(getActivity(), "NEW LOCATION", Toast.LENGTH_SHORT).show();
        if (map != null) {
            //addMarker(location);
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
//        map.setMyLocationEnabled(true);
        Location location = null;


        if (provider == null) {
            Log.i("ASD", "Onmapre");

//            showLocatonDialog();
        }else {
            if (checkLocationPermission()) {
                Log.i("ASD", "str" + provider);


                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    //Request location updates:
                    location = locationManager.getLastKnownLocation(provider);
                } else if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    //Request location updates:
                    location = locationManager.getLastKnownLocation(provider);
                }
            }
        }

        //ako zelimo da rucno postavljamo markere to radimo
        //dodavajuci click listener
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                map.addMarker(new MarkerOptions()
                        .title("YOUR_POSITON")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .position(latLng));
                home.setFlat(true);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(latLng).zoom(14).build();

                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        //ako zelmo da reagujemo na klik markera koristimo marker click listener
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Log.i("ASD", "ASDASDASDSA");
            }
        });

        //ako je potrebno da reagujemo na pomeranje markera koristimo marker drag listener
        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                Toast.makeText(getActivity(), "Drag started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                Toast.makeText(getActivity(), "Dragging", Toast.LENGTH_SHORT).show();
                map.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(getActivity(), "Drag ended", Toast.LENGTH_SHORT).show();
            }
        });

        if (location != null) {
            addMarker(location);
        }
    }

    private void addMarker(Location location) {
        LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

        if (home != null) {
            home.remove();
        }

        home = map.addMarker(new MarkerOptions()
                .title("YOUR_POSITON")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .position(loc));
        home.setFlat(true);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(loc).zoom(14).build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public LatLng getLocationFromAddress(Context context, String strAddress)
    {
        Geocoder coder= new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try
        {
            address = coder.getFromLocationName(strAddress, 5);
            if(address==null)
            {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p1;

    }

    public void createRoute(){

        if(startCoordinates != null && endCoordinates != null){
            startMarker.showInfoWindow();
            if(route != null){
                route.remove();
            }
            new GetPathFromLocation(startCoordinates, endCoordinates, new DirectionPointListener() {
                @Override
                public void onPath(PolylineOptions polyLine) {
                    route = map.addPolyline(polyLine);

                }
            }).execute();

        }
    }

    public void createRide(){
        EditText sLocation = ((EditText)getView().findViewById(R.id.start_location_passenger));
        String sAddress = sLocation.getText().toString();
        LocationDTO startLocationDTO = new LocationDTO(sAddress, (float) startCoordinates.latitude, (float) startCoordinates.longitude);
        EditText eLocation = ((EditText)getView().findViewById(R.id.end_location_passenger));
        String eAddress = eLocation.getText().toString();
        LocationDTO endLocationDTO = new LocationDTO(eAddress, (float) endCoordinates.latitude, (float) endCoordinates.longitude);
        RideLocationDTO ridelocation = new RideLocationDTO(startLocationDTO, endLocationDTO);
        ArrayList<RideLocationDTO> locations = new ArrayList<>();
        locations.add(ridelocation);

        SharedPreferences pref = this.getActivity().getSharedPreferences("AirRide_preferences", Context.MODE_PRIVATE);
        Long id = Long.valueOf(pref.getString("id", ""));
        String jwt = pref.getString("accessToken", "");
        ArrayList<UserDTO> passengers = new ArrayList<>();
        ServiceUtils.passengerService.findUser("Bearer "+jwt, id).enqueue(new Callback<GetUserDTO>() {

            @Override
            public void onResponse(Call<GetUserDTO> call, Response<GetUserDTO> response) {
                userDTO = response.body();
                UserDTO passenger1 = new UserDTO(id.intValue(), userDTO.getEmail());
                passengers.add(passenger1);
            }

            @Override
            public void onFailure(Call<GetUserDTO> call, Throwable t) {
                Log.wtf("message fill data: ", t.getMessage());
            }
        });





        CheckBox pets = ((CheckBox)getView().findViewById(R.id.transporting_pets));
        CheckBox babies = ((CheckBox)getView().findViewById(R.id.transporting_babies));
        boolean transportingPets = pets.isChecked();
        boolean transportingBabies = babies.isChecked();
        RadioGroup vehicleType = ((RadioGroup)getView().findViewById(R.id.vehicle_type));
        String selectedVehicleType = "";
        switch (vehicleType.getCheckedRadioButtonId()){
            case R.id.standard:
                selectedVehicleType = "Standard";
                break;
            case R.id.luxury:
                selectedVehicleType = "Luxury";
                break;
            case R.id.van:
                selectedVehicleType = "Van";
                break;

        }

        Toast.makeText(getContext(), "Searching for driver", Toast.LENGTH_SHORT).show();
        SendRideDTO finalRide = new SendRideDTO(locations, passengers, selectedVehicleType,transportingBabies, transportingPets);
        ourRide = finalRide;

        ((PassengerMainActivity)getActivity()).checkIfRideIsAvailable(ourRide);
    }


}