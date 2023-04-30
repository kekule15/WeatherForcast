package com.example.weatherforcast.screens.main.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforcast.model.UnitModel
import com.example.weatherforcast.repository.SettingsDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: SettingsDBRepository) :
    ViewModel() {
    private val _favList = MutableStateFlow<List<UnitModel>>(emptyList())

    var favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUnits().distinctUntilChanged()
                .collect { listItems ->
                    if (listItems.isNullOrEmpty()) {
                        Log.d("Units", ":Units list is Empty ")
                    } else {
                        _favList.value = listItems
                        Log.d("Units", ":${favList.value} ")
                    }
                }
        }
    }

    fun addUnit(unitModel: UnitModel) = viewModelScope.launch {
        repository.addUnit(unitModel)
    }

    fun updateUnit(unitModel: UnitModel) = viewModelScope.launch {
        repository.updateUnit(unitModel)
    }


    fun deleteOneUnit(unitModel: UnitModel) = viewModelScope.launch {
        repository.deleteOneUnit(unitModel)
    }

}