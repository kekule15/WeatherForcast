package com.example.weatherforcast.screens.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.WeatherItem
import com.example.weatherforcast.model.WeatherModel
import com.example.weatherforcast.navigation.WeatherScreens
import com.example.weatherforcast.utils.formatDate
import com.example.weatherforcast.utils.formatDecimals
import com.example.weatherforcast.widgets.*

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel, city: String?) {
    Log.d("City", "MainScreen: $city")
    val weatherData = produceState<DataOrException<WeatherModel, Boolean, java.lang.Exception>>(
        initialValue = DataOrException(loading = true),

        ) {
        value = mainViewModel.getWeatherData(city.toString())
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weatherModel = weatherData.data!!, navController)
    }
}


@Composable
fun MainScaffold(weatherModel: WeatherModel, navController: NavController) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = weatherModel.city.name + ", ${weatherModel.city.country}",
            navController = navController,
            elevation = 5.dp,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            }
        )
    }) { paddingValues ->
        MainContent(Modifier.padding(paddingValues), data = weatherModel)
    }
}

@Composable
fun MainContent(modifier: Modifier, data: WeatherModel) {
    val weatherItem: WeatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(weatherItem.dt),
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp), shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = formatDecimals(weatherItem.temp.day) + "º",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold,
                )
                Text(
                    text = weatherItem.weather[0].main,
                    fontStyle = FontStyle.Italic
                )
            }

        }
        HumidityWindPressureRow(data = weatherItem)
        Divider()
        SunSetSunRiceRow(data = weatherItem)
        Text(
            text = "This Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Surface(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
        ) {
            LazyColumn(modifier = Modifier.padding(2.dp), contentPadding = PaddingValues(1.dp)) {

                items(items = data.list) { item: WeatherItem -> WeatherDetailRow(data = item) }
            }
        }
    }
}
