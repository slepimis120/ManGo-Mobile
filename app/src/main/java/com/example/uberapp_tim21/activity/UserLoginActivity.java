package com.example.uberapp_tim21.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.driver.DriverMainActivity;
import com.example.uberapp_tim21.activity.dto.LoginDTO;
import com.example.uberapp_tim21.activity.dto.TokensDTO;
import com.example.uberapp_tim21.activity.passenger.PassengerMainActivity;
import com.example.uberapp_tim21.activity.passenger.PassengerRegisterActivity;
import com.example.uberapp_tim21.activity.service.ServiceUtils;

import com.auth0.android.jwt.JWT;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Button loginBtn = findViewById(R.id.loginBtn);
        editEmail= findViewById(R.id.editTextTextEmailAddress);
        editPassword = findViewById(R.id.editTextTextPassword);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button registerBtn = findViewById(R.id.login_registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, PassengerRegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private void attemptLogin(){
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        LoginDTO loginDTO = new LoginDTO(email, password);

        ServiceUtils.userService.login(loginDTO).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200){

                    try {
                        String message = response.body().string();
                        TokensDTO token = new Gson().fromJson(message, TokensDTO.class);
                        sharedPreferences = getSharedPreferences("AirRide_preferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        Log.i("Response code", response.code() + " ");
                        Log.i("Message", "User successfully logged in");

                        editor.putString("accessToken", token.getAccessToken());
                        editor.apply();

                        switchActivity();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }




                }else if(response.code() == 400){
                    editEmail.setText("");
                    editPassword.setText("");
                    editEmail.requestFocus();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(UserLoginActivity.this, "Bad request", Toast.LENGTH_SHORT).show();
                Logger.getLogger(UserLoginActivity.class.getName()).log(Level.SEVERE, "ERROR", t);
            }

        });
    }

    private void switchActivity(){
        String token = sharedPreferences.getString("accessToken", null);
        if(token != null){
            JWT jwt = new JWT(token);

            String role = jwt.getClaim("role").asString();

            String id = jwt.getClaim("id").asString();

            sharedPreferences.edit().putString("id", id).apply();
            sharedPreferences.edit().putString("role", role).apply();
            sharedPreferences.edit().putString("email", editEmail.getText().toString()).apply();
            Log.d("LOGIN", getSharedPreferences("AirRide_preferences", Context.MODE_PRIVATE).getString("id", null));
            if(role.equals("PASSENGER")){
                startActivity(new Intent(UserLoginActivity.this, PassengerMainActivity.class));
            }else{
                startActivity(new Intent(UserLoginActivity.this, DriverMainActivity.class));
            }

        }

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