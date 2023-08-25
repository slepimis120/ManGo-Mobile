package com.example.uberapp_tim21.activity.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.dto.ExpandedUserDTO;
import com.example.uberapp_tim21.activity.model.User;
import com.example.uberapp_tim21.activity.service.ServiceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverAccountEditActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    EditText address, phone, password;
    User driver;
    Button submit;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account_edit);
        getSupportActionBar().hide();

        address = findViewById(R.id.user_address);
        phone = findViewById(R.id.user_phone);
        password = findViewById(R.id.user_password);
        submit = findViewById(R.id.saveBtn);
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fillData();
        setListener();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_navbar_profile:
                startActivity(new Intent(DriverAccountEditActivity.this, DriverAccountActivity.class));
                break;
            case R.id.bottom_navbar_home:
                startActivity(new Intent(DriverAccountEditActivity.this, DriverMainActivity.class));
                break;
            case R.id.bottom_navbar_inbox:
                startActivity(new Intent(DriverAccountEditActivity.this, DriverInboxActivity.class));
                break;
            case R.id.bottom_navbar_history:
                startActivity(new Intent(DriverAccountEditActivity.this, DriverRideHistoryActivity.class));
                break;
        }
        return true;
    }

    private void fillData(){
        SharedPreferences pref = getSharedPreferences("AirRide_preferences", Context.MODE_PRIVATE);
        String jwt = pref.getString("accessToken", "");
        Long id = Long.valueOf(pref.getString("id", ""));
        ServiceUtils.driverService.getDriverInfo("Bearer "+jwt, id).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                driver = response.body();
                setParameters(driver);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.wtf("message fill data: ", t.getMessage());
            }
        });
    }

    private void setParameters(User user){
        phone.setText(user.getPhoneNumber());
        address.setText(user.getAddress());
        password.setText(user.getPassword());
//        if(driver.getProfilePhoto() != null){
//            byte[] bytes = Base64.decode(driver.getProfilePhoto(), Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//            set.setImageBitmap(bitmap);
//        }
    }

    private void setListener(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent i = new Intent(DriverAccountEditActivity.this, DriverAccountActivity.class);
                startActivity(i);
            }
        });
    }

    private void saveData(){
        SharedPreferences pref = getSharedPreferences("AirRide_preferences", Context.MODE_PRIVATE);
        String jwt = pref.getString("accessToken", "");
        Long id = Long.valueOf(pref.getString("id", ""));
        String phoneString = phone.getText().toString();
        System.out.println(phoneString);
        if(phoneString.equals("")){
            phoneString = driver.getPhoneNumber();
        }
        String addressString = address.getText().toString();
        if(addressString.equals("")){
            addressString = driver.getAddress();
        }
        String passwordString = password.getText().toString();
        if(passwordString.equals("")){
            passwordString = driver.getPassword();
        }
        ServiceUtils.driverService.saveDriverInfo("Bearer "+jwt, id, new ExpandedUserDTO(driver.getName(), driver.getLastName(), driver.getProfilePhoto(), phoneString, driver.getEmail(), addressString, passwordString)).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}