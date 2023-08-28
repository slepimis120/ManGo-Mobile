package com.example.uberapp_tim21.activity.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.dto.ReportDTO;
import com.example.uberapp_tim21.activity.dto.StatisticsDTO;
import com.example.uberapp_tim21.activity.dto.StatisticsDatesDTO;
import com.example.uberapp_tim21.activity.model.User;
import com.example.uberapp_tim21.activity.service.ServiceUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverAccountStatsActivity extends AppCompatActivity {

    TextView declinedRides, acceptedRides, workHours, pay;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account_stats);
        getSupportActionBar().hide();

        declinedRides = findViewById(R.id.rides_declined);
        acceptedRides = findViewById(R.id.rides_accepted);
        workHours = findViewById(R.id.work_hours);
        pay = findViewById(R.id.pay);

        addListenerOnButton();

        ((RadioButton)radioGroup.getChildAt(2)).setChecked(true);


    }

    public void addListenerOnButton() {

        radioGroup = findViewById(R.id.radiogroup);
        StatisticsDatesDTO statisticsDatesDTO = new StatisticsDatesDTO();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = dateFormat.format(new Date());
        statisticsDatesDTO.setStartDate(startDate);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == 0){
                LocalDate localDate = LocalDate.now();
                Date endDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
                String endDateString = dateFormat.format(endDate);
                statisticsDatesDTO.setEndDate(endDateString);

            } else if (checkedId == 1) {
                LocalDate localDate = LocalDate.now().plusDays(-7);
                Date endDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
                String endDateString = dateFormat.format(endDate);
                statisticsDatesDTO.setEndDate(endDateString);
            } else {
                LocalDate localDate = LocalDate.now().plusDays(-30);
                Date endDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
                String endDateString = dateFormat.format(endDate);
                statisticsDatesDTO.setEndDate(endDateString);
            }
            SharedPreferences pref = getSharedPreferences("AirRide_preferences", Context.MODE_PRIVATE);
            String jwt = pref.getString("accessToken", "");
            Long id = Long.valueOf(pref.getString("id", ""));
            ServiceUtils.driverService.getStats("Bearer "+jwt, id,statisticsDatesDTO).enqueue(new Callback<StatisticsDTO>() {

                @Override
                public void onResponse(Call<StatisticsDTO> call, Response<StatisticsDTO> response) {
                    setParameters(response.body());
                }

                @Override
                public void onFailure(Call<StatisticsDTO> call, Throwable t) {
                    Log.wtf("message fill data: ", t.getMessage());
                }
            });
        });
    }

    @SuppressLint("SetTextI18n")
    private void setParameters(StatisticsDTO statisticsDTO) {
        declinedRides.setText("Rides Declined: " + statisticsDTO.getCancelledRides());
        acceptedRides.setText("Rides Accepted: " + statisticsDTO.getAcceptedRides());
        workHours.setText("Work Hours: " + statisticsDTO.getWorkTime());
        pay.setText("Pay: " + statisticsDTO.getEarnings());
    }
}