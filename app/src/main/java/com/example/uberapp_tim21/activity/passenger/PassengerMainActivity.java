package com.example.uberapp_tim21.activity.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.uberapp_tim21.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PassengerMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
<<<<<<< HEAD
    //PassengerProfileFragment profileFragment = new PassengerProfileFragment();
    //PassengerHomeFragment homeFragment = new PassengerHomeFragment();
    //PassengerInboxFragment inboxFragment = new PassengerInboxFragment();
    //PassengerHistoryFragment historyFragment = new PassengerHistoryFragment();
    //Fragment currentFragment;


=======
>>>>>>> parent of 9ceb3b8 (Created mapping of inputted addresses and drawing a route between them)

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main);
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bottom_navbar_home);
        //currentFragment = homeFragment;
        loadFragment(new PassengerHomeFragment());
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment currentFragment = null;
        switch (item.getItemId()) {
            case R.id.bottom_navbar_profile:
                currentFragment = new PassengerProfileFragment();
                break;
            case R.id.bottom_navbar_home:
                currentFragment = new PassengerHomeFragment();
                break;
            case R.id.bottom_navbar_inbox:
                currentFragment = new PassengerInboxFragment();
                break;
            case R.id.bottom_navbar_history:
                currentFragment = new PassengerHistoryFragment();
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