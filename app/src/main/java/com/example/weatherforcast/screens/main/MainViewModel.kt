package com.example.weatherforcast.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.WeatherModel
import com.example.weatherforcast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    suspend fun getWeatherData(city: String): DataOrException<WeatherModel, Boolean, java.lang.Exception> {
        return repository.getWeather(cityQuery = city)
    }

//    val data: MutableState<DataOrException<WeatherModel, Boolean, java.lang.Exception>> =
//        mutableStateOf(
//            DataOrException(null, true, java.lang.Exception(""))
//        )
//
//    init {
//        loaderWeather()
//    }
//
//    private fun loaderWeather() {
//        getWeather("seattle")
//    }
//
//    private fun getWeather(query: String) {
//        viewModelScope.launch {
//            if (query.isEmpty())return@launch
//            data.value.loading = true
//            data.value = repository.getWeather(query)
//            if (data.value.data.toString().isNotEmpty()) data.value.loading = false
//        }
//        Log.d("TAG", "getWeather: ${data.value.data}")
//    }
}