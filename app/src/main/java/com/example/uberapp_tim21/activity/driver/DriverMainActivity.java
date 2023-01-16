package com.example.uberapp_tim21.activity.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.dto.RideDTO;
import com.example.uberapp_tim21.activity.dto.SendRejectionDTO;
import com.example.uberapp_tim21.activity.service.ServiceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    Integer id = 1;
    BottomNavigationView bottomNavigationView;
    private AlertDialog.Builder dialogueBuilder;
    private AlertDialog dialog;
    private Button decline, accept, cancel;
    private TextView popup_passenger_number, popup_distance, popup_locations, popup_price;
    private TextInputLayout insertText;
    private RideDTO rideDTO = null;
    private boolean hasPendingRide = false;
    private boolean hasActiveRide = false;

    final Handler handler = new Handler();
    final int delay = 10000;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bottom_navbar_home);


        handler.postDelayed(new Runnable() {
            public void run() {
                ifRideIsAvailable();
                if(rideDTO != null && !hasPendingRide){
                    getNewRide();
                    hasPendingRide = true;
                    handler.removeCallbacksAndMessages(null);
                }
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    @Override
    public void onStart() {
        super.onStart();
        findViewById(R.id.driver_ride_details).setVisibility(View.GONE);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.bottom_navbar_profile:
                fragment = new DriverProfileFragment();
                break;
            case R.id.bottom_navbar_home:
                fragment = new DriverCurrentRideFragment();
                break;
            case R.id.bottom_navbar_inbox:
                fragment = new DriverInboxFragment();
                break;
            case R.id.bottom_navbar_history:
                fragment = new DriverHistoryFragment();
                break;
        }
        if (fragment != null) {
            loadFragment(fragment);
        }
        return true;
    }

    void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.driver_content, fragment).commit();
    }

    public void getNewRide(){

        dialogueBuilder = new AlertDialog.Builder(this);
        final View ridePopupView = getLayoutInflater().inflate(R.layout.popup, null);


        popup_passenger_number = (TextView) ridePopupView.findViewById(R.id.popup_passenger_number);
        popup_distance = (TextView) ridePopupView.findViewById(R.id.popup_distance);
        popup_locations = (TextView) ridePopupView.findViewById(R.id.popup_locations);
        popup_price = (TextView) ridePopupView.findViewById(R.id.popup_price);

        accept = (Button) ridePopupView.findViewById(R.id.popup_accept);
        decline = (Button) ridePopupView.findViewById(R.id.popup_decline);

        popup_passenger_number.setText(popup_passenger_number.getText() + " " + rideDTO.getPassengers().size());
        popup_distance.setText(popup_distance.getText() + " " + rideDTO.getEstimatedTimeInMinutes());
        popup_locations.setText(popup_locations.getText() + " " + rideDTO.getLocations().get(0).getDeparture().getAddress() + " - " + rideDTO.getLocations().get(0).getDestination().getAddress());
        popup_price.setText(popup_price.getText() + " " + rideDTO.getTotalCost() + "RSD");

        dialogueBuilder.setView(ridePopupView);
        dialog = dialogueBuilder.create();
        dialog.show();

        accept.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                hasActiveRide = true;
                Call<RideDTO> call = ServiceUtils.reviewerService.acceptRide(rideDTO.getId());
                call.enqueue(new Callback<RideDTO>(){
                    @Override
                    public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {

                        TextView start = findViewById(R.id.driver_start_location);
                        start.setText(rideDTO.getLocations().get(0).getDeparture().getAddress());
                        TextView end = findViewById(R.id.driver_end_location);
                        end.setText(rideDTO.getLocations().get(0).getDestination().getAddress());
                        TextView price = findViewById(R.id.driver_price);
                        price.setText("Price: " + rideDTO.getTotalCost() + " din") ;
                        TextView duration = findViewById(R.id.driver_duration);
                        duration.setText("Duration: " + rideDTO.getEstimatedTimeInMinutes() + " min") ;
                        TextView distance = findViewById(R.id.driver_distance);
                        duration.setText("Distance: " + rideDTO.getEstimatedTimeInMinutes() + " km") ;
                        findViewById(R.id.driver_ride_details).setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onFailure(Call<RideDTO> call, Throwable t) {
                        Log.d("REZ", t.getMessage() != null?t.getMessage():"error");
                    }
                });
                dialog.hide();
            }
        });

        decline.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                hasPendingRide = false;
                dialog.hide();
                askForReason();
            }
        });
    }


    public void askForReason(){
        dialogueBuilder = new AlertDialog.Builder(this);
        final View ridePopupView = getLayoutInflater().inflate(R.layout.popup_cancel, null);

        insertText = (TextInputLayout) ridePopupView.findViewById(R.id.popup_reason_cancel);
        cancel = (Button) ridePopupView.findViewById(R.id.cancelButton);

        dialogueBuilder.setView(ridePopupView);
        dialog = dialogueBuilder.create();
        dialog.show();

        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SendRejectionDTO sendRejectionDTO = new SendRejectionDTO(insertText.getEditText().getText().toString());
                Call<RideDTO> call = ServiceUtils.reviewerService.cancelRide(rideDTO.getId(), sendRejectionDTO);
                call.enqueue(new Callback<RideDTO>(){
                    @Override
                    public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {
                        dialog.hide();
                    }

                    @Override
                    public void onFailure(Call<RideDTO> call, Throwable t) {
                        Log.d("REZ", t.getMessage() != null?t.getMessage():"error");
                    }
                });
            }
        });

    }

    public boolean ifRideIsAvailable(){
        Call<RideDTO> call = ServiceUtils.reviewerService.checkIfDriverHasRide(id);
        call.enqueue(new Callback<RideDTO>(){
            @Override
            public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {
                if(response.body() != null){
                    rideDTO = response.body();
                }
            }

            @Override
            public void onFailure(Call<RideDTO> call, Throwable t) {
                Log.d("REZ", t.getMessage() != null?t.getMessage():"error");
            }
        });
        return true;
    }
}