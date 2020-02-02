package com.marquez.detroitlabs.weatherapp.model

import androidx.room.Embedded
import androidx.room.Entity

@Entity
data class Forecast(
    @Embedded
    var city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastDetail>,
    val message: Int
)