package com.example.pogodamain;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pogodamain.Api.api;
import com.example.pogodamain.model.Whether;

import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    com.example.pogodamain.Api.api api;
    TextView temp, timeZonem, LocationText;
    LocationManager locationManager;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(api.class);

        temp = findViewById(R.id.textViewTemp1);
        timeZonem = findViewById(R.id.textViewTimeZone1);
        LocationText = findViewById(R.id.textViewCordinat1);



        api.getWeather(10, 10, "e012f24a2044e4fe9504ac4db488f059","metric").enqueue(new Callback<Whether>() {
            @Override
            public void onResponse(Call<Whether> call, Response<Whether> response) {
                if(response.code() == 200){
                    Whether whether = response.body();
                    temp.setText("температура: " + whether.getCurrent().getTemp()+"\nощущается как: " + whether.getCurrent().getFeels_like());
                    timeZonem.setText("часовой пояс: " + whether.getTimezone());
                }
            }

            @Override
            public void onFailure(Call<Whether> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        locationManager = getSystemService(LocationManager.class);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},0);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

            }
        });


    }



}