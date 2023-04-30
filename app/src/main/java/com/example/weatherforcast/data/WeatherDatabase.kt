package com.example.weatherforcast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherforcast.model.FavouriteModel
import com.example.weatherforcast.model.UnitModel

@Database(entities = [FavouriteModel::class, UnitModel::class], version = 2, exportSchema = false)
abstract class WeatherDatabase() : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    abstract fun settingsDao(): SettingsDao
}