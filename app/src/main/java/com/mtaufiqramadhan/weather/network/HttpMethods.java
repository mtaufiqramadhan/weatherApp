package com.mtaufiqramadhan.weather.network;

import com.mtaufiqramadhan.weather.model.WeatherData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HttpMethods {

    @GET("weather")
    Observable<WeatherData> getWeatherData
            (@Query("q") String q,
             @Query("appid") String appid);

}
