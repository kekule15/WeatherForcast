package com.example.weatherforcast.di

import android.content.Context
import androidx.room.Room
import com.example.weatherforcast.data.SettingsDao
import com.example.weatherforcast.data.WeatherDao
import com.example.weatherforcast.data.WeatherDatabase
import com.example.weatherforcast.network.WeatherAPI
import com.example.weatherforcast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesSettingDao(weatherDatabase: WeatherDatabase): SettingsDao =
        weatherDatabase.settingsDao()

    @Provides
    @Singleton
    fun providesWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao =
        weatherDatabase.weatherDao()

    @Provides
    @Singleton
    fun providesWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context = context,
            WeatherDatabase::class.java,
            "weather_database"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(
                WeatherAPI::class.java
            )
    }

}