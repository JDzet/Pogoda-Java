package com.example.pogodamain.Api;

import com.example.pogodamain.model.Whether;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api {
    @GET("/data/2.5/onecall")
    Call<Whether> getWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("appid")
            String token, @Query("units")String unit);
}
