package com.example.weatherforcast.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.WeatherModel

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {

    val weatherData = produceState<DataOrException<WeatherModel, Boolean, java.lang.Exception>>(
        initialValue = DataOrException(loading = true),

        ) {
        value = mainViewModel.getWeatherData("seattle")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weatherModel = weatherData.data!!, navController)
    }
}


@Composable
fun MainScaffold(weatherModel: WeatherModel, navController: NavController) {

 Scaffold(topBar = {}) {paddingValues ->
     MainContent(Modifier.padding(paddingValues), data = weatherModel)
 }
}

@Composable
fun MainContent(modifier: Modifier, data: WeatherModel) {
    Text(text = data.city.name)
}
