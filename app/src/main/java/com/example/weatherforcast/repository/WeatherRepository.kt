package com.example.weatherforcast.repository

import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.WeatherModel
import com.example.weatherforcast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI) {


    suspend fun getWeather(cityQuery: String): DataOrException<WeatherModel, Boolean, java.lang.Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}