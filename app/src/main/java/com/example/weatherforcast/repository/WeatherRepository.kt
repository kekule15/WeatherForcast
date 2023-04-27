package com.example.weatherforcast.repository

import android.util.Log
import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.WeatherModel
import com.example.weatherforcast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI) {


    suspend fun getWeather(cityQuery: String): DataOrException<WeatherModel, Boolean, java.lang.Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            Log.d("Error", "getWeather: $e")
            return DataOrException(e = e)
        }
        Log.d("Data", "getWeather: $response")
        return DataOrException(data = response)
    }
}