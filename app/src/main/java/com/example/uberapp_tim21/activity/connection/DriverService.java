package com.example.uberapp_tim21.activity.connection;

import com.example.uberapp_tim21.activity.dto.ExpandedUserDTO;
import com.example.uberapp_tim21.activity.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DriverService {

    @GET("driver/{id}")
    Call<User> getDriverInfo(@Header("authorization")String token, @Path("id")Long id);

    @PUT("driver/{id}")
    Call<User> saveDriverInfo(@Header("authorization") String jwt,@Path("id")Long id,@Body ExpandedUserDTO expandedUserDTO);
}
