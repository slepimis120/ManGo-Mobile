package com.example.uberapp_tim21.activity.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.dto.ResponseRideDTO;
import com.example.uberapp_tim21.activity.dto.RideDTO;
import com.example.uberapp_tim21.activity.dto.SendRideDTO;
import com.example.uberapp_tim21.activity.service.ServiceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    PassengerHomeFragment homeFragment;
    PassengerProfileFragment profileFragment;
    PassengerInboxFragment inboxFragment;
    PassengerHistoryFragment historyFragment;
    Fragment currentFragment;
    SendRideDTO ourRide;

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



        Call<RideDTO> call = ServiceUtils.reviewerService.getPassengerActiveRide(id);
        call.enqueue(new Callback<RideDTO>(){
            @Override
            public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {
                loadFragment(new PassengerHomeFragment());
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

    public void checkIfRideIsAvailable(SendRideDTO ride){
        SharedPreferences pref = getSharedPreferences("AirRide_preferences", Context.MODE_PRIVATE);
        String jwt = pref.getString("accessToken", "");
        Long id = Long.valueOf(pref.getString("id", ""));
        ServiceUtils.rideService.createRide("Bearer "+jwt, ride).enqueue(new Callback<ResponseRideDTO>() {

            @Override
            public void onResponse(Call<ResponseRideDTO> call, Response<ResponseRideDTO> response) {
                setDoesRideExist(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<ResponseRideDTO> call, Throwable t) {
                Log.wtf("message fill data: ", t.getMessage());
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