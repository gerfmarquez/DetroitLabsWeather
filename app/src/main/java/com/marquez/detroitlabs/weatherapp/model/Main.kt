package com.marquez.detroitlabs.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    @PrimaryKey(autoGenerate = true)
    var mainId: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)