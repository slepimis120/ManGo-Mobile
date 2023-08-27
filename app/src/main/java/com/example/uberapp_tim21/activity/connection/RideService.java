package com.example.uberapp_tim21.activity.connection;

import com.example.uberapp_tim21.activity.dto.ResponseRideDTO;
import com.example.uberapp_tim21.activity.dto.SendRideDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RideService {

    @POST("ride")
    Call<ResponseRideDTO> createRide(@Header("authorization")String token, @Body SendRideDTO sendRideDTO);

}
