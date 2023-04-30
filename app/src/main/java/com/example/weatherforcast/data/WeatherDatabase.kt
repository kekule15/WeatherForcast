package com.example.weatherforcast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherforcast.model.FavouriteModel

@Database(entities = [FavouriteModel::class], version = 1, exportSchema = false)
abstract class WeatherDatabase() : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}