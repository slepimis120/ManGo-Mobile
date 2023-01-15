package com.example.uberapp_tim21.activity.service;

import com.example.uberapp_tim21.activity.dto.RideDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ReviewerService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @GET(ServiceUtils.SERVICE_API_PATH + "ride/passenger/{id}/active")
    Call<RideDTO> getPassengerActiveRide(@Path(value="id") Integer id);
}
