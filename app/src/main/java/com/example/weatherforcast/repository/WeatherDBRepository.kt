package com.example.weatherforcast.repository

import com.example.weatherforcast.data.WeatherDao
import com.example.weatherforcast.model.FavouriteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDBRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getAllFavourites(): Flow<List<FavouriteModel>> = weatherDao.getAllFavourites()

    suspend fun addFavourite(favouriteModel: FavouriteModel) = weatherDao.addFavourite(favouriteModel)

    suspend fun updateFavourite(favouriteModel: FavouriteModel) = weatherDao.updateFavourite(favouriteModel)

    suspend fun deleteAllFavourites() = weatherDao.deleteAll()

    suspend fun  deleteOneFavourite(favouriteModel: FavouriteModel) = weatherDao.deleteFavById(favouriteModel)

    suspend fun getFavById(city: String): FavouriteModel = weatherDao.getFavById(city)
}