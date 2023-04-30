package com.example.weatherforcast.data

import androidx.room.*
import com.example.weatherforcast.model.FavouriteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query(value = "SELECT * from fav_tbl")
    fun getAllFavourites(): Flow<List<FavouriteModel>>

    @Query(value = "SELECT * from fav_tbl where city =:city")
    suspend fun getFavById(city: String): FavouriteModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(favourite: FavouriteModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: FavouriteModel)

    @Query(value = "DELETE from fav_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteFavById(favourite: FavouriteModel)
}