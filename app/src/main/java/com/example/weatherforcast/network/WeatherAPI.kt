package com.example.weatherforcast.network

import com.example.weatherforcast.model.WeatherModel
import com.example.weatherforcast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = Constants.API_KEY,
    ): WeatherModel
}