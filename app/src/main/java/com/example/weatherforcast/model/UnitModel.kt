package com.example.weatherforcast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "settings_tbl")
data class UnitModel(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "unit")
    var unit:String
)
