package com.example.uberapp_tim21.activity.driver;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
import com.example.uberapp_tim21.activity.model.NavItem;
import com.example.uberapp_tim21.activity.tools.FragmentTransition;

import java.util.ArrayList;

public class DriverMainActivity extends AppCompatActivity {
    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerPane;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        prepareMenu(mNavItems);

        mTitle = getTitle();
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerList = findViewById(R.id.navList);

        mDrawerPane = findViewById(R.id.drawerPane);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerList.setAdapter(adapter);

        TextView text = findViewById(R.id.driverState);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setIcon(R.drawable.splashdark);
            actionBar.setHomeAsUpIndicator(R.drawable.burger_icon);
            actionBar.setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("iReviewer");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Izborom na neki element iz liste, pokrecemo akciju
        if (savedInstanceState == null) {
            selectItemFromDrawer(0);
        }

        ToggleButton toggle = findViewById(R.id.stateBtn);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    text.setVisibility(View.VISIBLE);
                    text.setText("online");
                } else {
                    text.setVisibility(View.VISIBLE);
                    text.setText("offline");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Intent i = getIntent();
        if (i.getExtras() != null) {
            String intentFragment = i.getExtras().getString("loadFrg");
        }
    }


    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);
            Log.i("Pisi", String.valueOf(position));

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }



    private void selectItemFromDrawer(int position) {
        if(position == 0){
            //
        }else if(position == 1){
            //

        }else if(position == 2){
            //..
        }else if(position == 3){
            //..
        }else if(position == 4){
            //..
        }else if(position == 5){
            //...
        }else{
            Log.e("DRAWER", "Nesto van opsega!");
        }

        mDrawerList.setItemChecked(position, true);
        if(position != 5) // za sve osim za sync
        {
            setTitle(mNavItems.get(position).getmTitle());
        }
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    private void prepareMenu(ArrayList<NavItem> mNavItems) {
        mNavItems.add(new NavItem(getString(R.string.home), getString(R.string.home_long), R.drawable.home_icon));
        mNavItems.add(new NavItem(getString(R.string.account), getString(R.string.account_long), R.drawable.pen_icon));
        mNavItems.add(new NavItem(getString(R.string.inbox), getString(R.string.inbox_long), R.drawable.message_icon));
        mNavItems.add(new NavItem(getString(R.string.ride_history), getString(R.string.ride_history_long), R.drawable.history_icon));
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


}