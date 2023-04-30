package com.example.weatherforcast.screens.main.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforcast.model.FavouriteModel
import com.example.weatherforcast.repository.WeatherDBRepository
import com.example.weatherforcast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val repository: WeatherDBRepository) :
    ViewModel() {
    private val _favList = MutableStateFlow<List<FavouriteModel>>(emptyList())

    var favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavourites().distinctUntilChanged()
                .collect { listItems ->
                    if (listItems.isNullOrEmpty()) {
                        Log.d("Favourite", ":Favourite list is Empty ")
                    } else {
                        _favList.value = listItems
                        Log.d("Favourite", ":${favList.value} ")
                    }
                }
        }
    }

    fun addFavourite(favouriteModel: FavouriteModel) = viewModelScope.launch {
        repository.addFavourite(favouriteModel)
    }

    fun updateFavourite(favouriteModel: FavouriteModel) = viewModelScope.launch {
        repository.updateFavourite(favouriteModel)
    }

    fun getFavById(city:String) = viewModelScope.launch {
        repository.getFavById(city)
    }

    fun deleteOneFavourite(favouriteModel: FavouriteModel) = viewModelScope.launch {
        repository.deleteOneFavourite(favouriteModel)
    }

}