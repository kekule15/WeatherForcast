package com.example.weatherforcast.screens.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weatherforcast.navigation.WeatherScreens
import com.example.weatherforcast.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "About",
            icon = Icons.Default.ArrowBack,
            navController = navController,
            isMainScreen = false

        ) {
            navController.popBackStack()
        }
    }) { paddingValues ->
        Surface(
            Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = com.example.weatherforcast.R.string.about_app),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(id = com.example.weatherforcast.R.string.api_used),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}