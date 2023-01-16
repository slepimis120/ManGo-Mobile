package com.example.uberapp_tim21.activity.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.dto.RideDTO;
import com.example.uberapp_tim21.activity.dto.SendRideDTO;
import com.example.uberapp_tim21.activity.service.ServiceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{



    Integer id = 1;
    Boolean doesRideExist = false;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main);
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bottom_navbar_home);
        homeFragment = new PassengerHomeFragment();
        profileFragment = new PassengerProfileFragment();
        inboxFragment = new PassengerInboxFragment();
        historyFragment = new PassengerHistoryFragment();
        currentFragment = homeFragment;
        loadFragment(currentFragment);

        RelativeLayout content  = findViewById(R.id.passenger_content);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeFragment.ourRide != null){
                    checkIfRideIsAvailable(homeFragment.ourRide);
                }
            }
        });

        final View passengerHome  = getLayoutInflater().inflate(R.layout.fragment_passenger_home, null);
        Button findVehicleBtn = passengerHome.findViewById(R.id.find_vehicle_btn);
        findVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeFragment.ourRide != null){
                    checkIfRideIsAvailable(homeFragment.ourRide);
                }
            }
        });

        Call<RideDTO> call = ServiceUtils.reviewerService.getPassengerActiveRide(id);
        call.enqueue(new Callback<RideDTO>(){
            @Override
            public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {
                if(response.isSuccessful()){
                    loadFragment(new PassengerCurrentRideFragment());
                }else{
                    loadFragment(new PassengerHomeFragment());
                }
            }

            @Override
            public void onFailure(Call<RideDTO> call, Throwable t) {
                loadFragment(new PassengerHomeFragment());
            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        //checkIfRideIsAvailable();
        switch (item.getItemId()) {
            case R.id.bottom_navbar_profile:
                currentFragment = profileFragment;
                break;
            case R.id.bottom_navbar_home:
                currentFragment = homeFragment;
                if(this.doesRideExist){
                    fragment = new PassengerCurrentRideFragment();
                }else{
                    fragment = new PassengerHomeFragment();
                }
                break;
            case R.id.bottom_navbar_inbox:
                currentFragment = inboxFragment;
                break;
            case R.id.bottom_navbar_history:
                currentFragment = historyFragment;
                break;
        }
        if (currentFragment != null) {
            loadFragment(currentFragment);
        }
        return true;
    }
    void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.passenger_content, fragment).commit();
    }

    private void checkIfRideIsAvailable(SendRideDTO ride){
        //TODO: Implementirati da zapravo prima ID ulogovanog korisnika, ne random ID
        Call<SendRideDTO> call = ServiceUtils.reviewerService.getAvailableDrivers(ride);
        call.enqueue(new Callback<SendRideDTO>(){
            @Override
            public void onResponse(Call<SendRideDTO> call, Response<SendRideDTO> response) {
                System.out.println(doesRideExist + "2");
                setDoesRideExist(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<SendRideDTO> call, Throwable t) {
                setDoesRideExist(false);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setDoesRideExist(boolean doesRideExist){
        this.doesRideExist = doesRideExist;
    }
}