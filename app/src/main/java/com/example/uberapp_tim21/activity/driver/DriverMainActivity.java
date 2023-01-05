package com.example.uberapp_tim21.activity.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.widget.Toolbar;


import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.adapters.DrawerListAdapter;
import com.example.uberapp_tim21.activity.fragments.HistoryFragment;
import com.example.uberapp_tim21.activity.fragments.HomeFragment;
import com.example.uberapp_tim21.activity.fragments.InboxFragment;
import com.example.uberapp_tim21.activity.fragments.ProfileFragment;
import com.example.uberapp_tim21.activity.model.NavItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class DriverMainActivity extends AppCompatActivity {
    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerPane;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;
    BottomNavigationView bottomNavigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

      //  bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.person:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                        return true;

                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                        return true;

                    case R.id.inbox:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, inboxFragment).commit();
                        return true;
                    case R.id.history:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, historyFragment).commit();
                        return true;
                }
                return true;
            }
        });
    }
    HistoryFragment historyFragment = new HistoryFragment();
    HomeFragment homeFragment = new HomeFragment();
    InboxFragment inboxFragment = new InboxFragment();
    ProfileFragment profileFragment = new ProfileFragment();

}