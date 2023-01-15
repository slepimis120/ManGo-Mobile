package com.example.uberapp_tim21.activity.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.dto.RideDTO;
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
        checkIfRideIsAvailable();
        switch (item.getItemId()) {
            case R.id.bottom_navbar_profile:
                fragment = new PassengerProfileFragment();
                break;
            case R.id.bottom_navbar_home:
                if(this.doesRideExist){
                    fragment = new PassengerCurrentRideFragment();
                }else{
                    fragment = new PassengerHomeFragment();
                }
                break;
            case R.id.bottom_navbar_inbox:
                fragment = new PassengerInboxFragment();
                break;
            case R.id.bottom_navbar_history:
                fragment = new PassengerHistoryFragment();
                break;
        }
        if (fragment != null) {
            loadFragment(fragment);
        }
        return true;
    }
    void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.passenger_content, fragment).commit();
    }

    private void checkIfRideIsAvailable(){
        //TODO: Implementirati da zapravo prima ID ulogovanog korisnika, ne random ID
        System.out.println("AA");
        Call<RideDTO> call = ServiceUtils.reviewerService.getPassengerActiveRide(id);
        call.enqueue(new Callback<RideDTO>(){
            @Override
            public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {
                System.out.println(doesRideExist + "2");
                setDoesRideExist(response.isSuccessful());
                System.out.println(doesRideExist + "3");
            }

            @Override
            public void onFailure(Call<RideDTO> call, Throwable t) {
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