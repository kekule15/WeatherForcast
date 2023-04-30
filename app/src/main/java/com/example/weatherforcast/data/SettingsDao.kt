package com.example.weatherforcast.data

import androidx.room.*
import com.example.weatherforcast.model.UnitModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Query(value = "SELECT * from settings_tbl")
    fun getAllUnits(): Flow<List<UnitModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUnit(unitModel: UnitModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unitModel: UnitModel)

    @Query(value = "DELETE from settings_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteUnitById(unitModel: UnitModel)
}