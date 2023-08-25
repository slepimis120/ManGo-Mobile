package com.example.uberapp_tim21.activity.connection;

import com.example.uberapp_tim21.activity.dto.LoginDTO;
import com.example.uberapp_tim21.activity.dto.TokensDTO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("user/login")
    Call<ResponseBody> login(@Body LoginDTO loginDTO);
}
