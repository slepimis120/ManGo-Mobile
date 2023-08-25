package com.example.uberapp_tim21.activity.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.model.User;
import com.example.uberapp_tim21.activity.service.ServiceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverAccountActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    TextView fullName, email, phoneNum, address;
    BottomNavigationView bottomNavigationView;
    ImageView editImage;
    User driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account);
        getSupportActionBar().hide();

        fullName = findViewById(R.id.name);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phoneNum = findViewById(R.id.phone_number);
        editImage = findViewById(R.id.imageButton3);
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bottom_navbar_profile);

        editImage.setOnClickListener(v -> {
            Intent mainIntent = new Intent(DriverAccountActivity.this, DriverAccountEditActivity.class);
            startActivity(mainIntent);
        });

        fillData();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_navbar_home:
                startActivity(new Intent(DriverAccountActivity.this, DriverMainActivity.class));
                break;
            case R.id.bottom_navbar_inbox:
                startActivity(new Intent(DriverAccountActivity.this, DriverInboxActivity.class));
                break;
            case R.id.bottom_navbar_history:
                startActivity(new Intent(DriverAccountActivity.this, DriverRideHistoryActivity.class));
                break;
        }
        return true;
    }

    void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.driver_content, fragment).commit();
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
        String fn = user.getName() + " " + user.getLastName();
        fullName.setText(fn);
        email.setText(user.getEmail());
        address.setText(user.getAddress());
        phoneNum.setText(user.getPhoneNumber());
//        if(driver.getProfilePhoto() != null){
//            byte[] bytes = Base64.decode(driver.getProfilePhoto(), Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//            set.setImageBitmap(bitmap);
//        }
    }
}