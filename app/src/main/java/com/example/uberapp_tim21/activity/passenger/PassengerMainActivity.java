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

import com.example.uberapp_tim21.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PassengerMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    PassengerHomeFragment homeFragment;
    PassengerProfileFragment profileFragment;
    PassengerInboxFragment inboxFragment;
    PassengerHistoryFragment historyFragment;
    Fragment currentFragment;


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



    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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


}