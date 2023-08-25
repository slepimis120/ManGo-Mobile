package com.example.uberapp_tim21.activity.service;

import com.example.uberapp_tim21.activity.connection.DriverService;
import com.example.uberapp_tim21.activity.connection.UserService;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceUtils {

    public static final String SERVICE_API_PATH = "http://192.168.0.36:8080/api/";
    public static final String ADD = "add";
    public static final String GET = "get";

    public static OkHttpClient test(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();

        return client;
    }

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVICE_API_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(test())
            .build();

    public static ReviewerService reviewerService = retrofit.create(ReviewerService.class);

    public static UserService userService = retrofit.create(UserService.class);

    public static DriverService driverService = retrofit.create(DriverService.class);


}
