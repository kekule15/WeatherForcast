package com.example.weatherforcast.widgets

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforcast.model.FavouriteModel
import com.example.weatherforcast.navigation.WeatherScreens
import com.example.weatherforcast.screens.main.favourite.FavouriteViewModel

//@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }
    var context = LocalContext.current
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle2
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                }
                IconButton(onClick = { showDialog.value = true }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More Icon")
                }

            } else Box {}
        },
        backgroundColor = Color.Transparent,
        navigationIcon = {
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = null, modifier = Modifier.clickable {
                    onButtonClicked.invoke()
                })
            }
            if (isMainScreen) {
                var dataList = title.split(",")
                var isAlreadyFavList =
                    favouriteViewModel.favList.collectAsState().value.filter { item -> item.city == dataList[0] }
                if (isAlreadyFavList.isNullOrEmpty()) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favourite Icon",
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .scale(0.9f)
                            .clickable {

                                favouriteViewModel
                                    .addFavourite(
                                        FavouriteModel(
                                            city = dataList[0],
                                            country = dataList[1]
                                        )
                                    )
                                    .run {
                                        Toast
                                            .makeText(
                                                context,
                                                "Added to Favourite List",
                                                Toast.LENGTH_LONG
                                            )
                                            .show()

                                    }
                            },
                        tint = Color.Red.copy(alpha = 0.6f)

                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favourite Icon",
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .scale(0.9f)
                            .clickable {
                                favouriteViewModel
                                    .deleteOneFavourite(
                                        FavouriteModel(
                                            city = dataList[0],
                                            country = dataList[1]
                                        )
                                    )
                                    .run {
                                        Toast
                                            .makeText(
                                                context,
                                                "City removed from Favourite List",
                                                Toast.LENGTH_LONG
                                            )
                                            .show()

                                    }
                            },
                        tint = Color.Red.copy(alpha = 0.6f)

                    )
                }
            }
        },
        elevation = elevation
    )
}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val itemsList = listOf(
        "About", "Favourites", "Settings"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopCenter)
            .absolutePadding(top = 45.dp, right = 10.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(140.dp)
                .background(
                    Color.White
                )
        ) {
            itemsList.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = when (text) {
                            "About" -> Icons.Default.Info
                            "Favourites" -> Icons.Default.FavoriteBorder
                            else -> Icons.Default.Settings
                        }, contentDescription = null,
                        tint = Color.LightGray
                    )
                    Text(
                        text = text,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                when (text) {
                                    "About" -> WeatherScreens.AboutScreen.name
                                    "Favourites" -> WeatherScreens.FavouriteScreen.name
                                    else -> WeatherScreens.SettingsScreen.name
                                }
                            )
                        },
                        fontWeight = FontWeight(300)
                    )
                }
            }
        }
    }
}
