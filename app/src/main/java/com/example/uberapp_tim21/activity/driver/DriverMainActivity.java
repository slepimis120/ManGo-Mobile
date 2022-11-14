package com.example.uberapp_tim21.activity.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.uberapp_tim21.R;

public class DriverMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        TextView text = findViewById(R.id.driverState);

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
}