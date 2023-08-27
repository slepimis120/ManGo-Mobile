package com.example.uberapp_tim21.activity.connection;

import com.example.uberapp_tim21.activity.dto.GetUserDTO;
import com.example.uberapp_tim21.activity.dto.LoginDTO;
import com.example.uberapp_tim21.activity.dto.UserDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PassengerService {

    @GET("passenger/{id}")
    Call<GetUserDTO> findUser (@Header("authorization") String jwt, @Path("id")Long id);

}
