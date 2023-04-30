package com.example.weatherforcast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "fav_tbl")
data class FavouriteModel(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "city")
    var city: String,
    @ColumnInfo(name = "country")
    var country: String
)
