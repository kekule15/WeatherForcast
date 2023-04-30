package com.example.weatherforcast.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherforcast.R
import com.example.weatherforcast.model.FavouriteModel
import com.example.weatherforcast.navigation.WeatherScreens
import com.example.weatherforcast.screens.main.favourite.FavouriteViewModel
import com.example.weatherforcast.widgets.WeatherAppBar

@Composable
fun FavouriteScreen(
    navController: NavHostController,
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Favourite Cities",
            icon = Icons.Default.ArrowBack,
            navController = navController,
            isMainScreen = false

        ) {
            navController.popBackStack()
        }
    }) { paddingValues ->
        Surface(
            modifier =
            Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var list = favouriteViewModel.favList.collectAsState().value

                LazyColumn {
                    items(items = list) {
                        CityRow(it, navController = navController, favouriteViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(
    favouriteModel: FavouriteModel,
    navController: NavHostController,
    favouriteViewModel: FavouriteViewModel,
) {
    Surface(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favouriteModel.city}")
            },
        shape = RoundedCornerShape(6.dp),
        color = Color.LightGray
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = favouriteModel.city, modifier = Modifier.padding(start = 4.dp))
            Surface(
                modifier = Modifier.padding(2.dp),
                shape = CircleShape,
                color = Color.Cyan.copy(alpha = 0.5f)
            ) {
                Text(
                    text = favouriteModel.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Icon",
                modifier = Modifier
                    .padding(0.dp)
                    .clickable {
                        favouriteViewModel.deleteOneFavourite(favouriteModel)
                    },
                tint = Color.Red.copy(alpha = 0.7f)
            )
        }
    }
}
