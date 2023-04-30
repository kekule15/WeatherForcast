package com.example.weatherforcast.repository

import com.example.weatherforcast.data.SettingsDao
import com.example.weatherforcast.model.FavouriteModel
import com.example.weatherforcast.model.UnitModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsDBRepository @Inject constructor(private val settingsDao: SettingsDao) {
    fun getAllUnits(): Flow<List<UnitModel>> = settingsDao.getAllUnits()

    suspend fun addUnit(unitModel: UnitModel) = settingsDao.addUnit(unitModel)

    suspend fun updateUnit(unitModel: UnitModel) = settingsDao.updateUnit(unitModel)


    suspend fun deleteAllUnits() = settingsDao.deleteAll()

    suspend fun deleteOneUnit(unitModel: UnitModel) = settingsDao.deleteUnitById(unitModel)


}