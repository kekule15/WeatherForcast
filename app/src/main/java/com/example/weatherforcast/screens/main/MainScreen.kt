package com.example.weatherforcast.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.weatherforcast.screens.main.MainViewModel

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel){
    Text(text = "Main Screen")
}