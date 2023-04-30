package com.example.weatherforcast.screens.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherforcast.screens.main.favourite.SettingsViewModel

@Composable
fun SettingsScreen(navController: NavHostController, settingsViewModel: SettingsViewModel = hiltViewModel()) {
    Text(text = "Settings Page")
}