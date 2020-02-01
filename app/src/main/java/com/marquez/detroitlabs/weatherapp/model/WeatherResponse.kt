package com.marquez.detroitlabs.weatherapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherResponse (
    val cod: Int,
    @Embedded
    var coord: Coord,
    val dt: Int,
    @PrimaryKey
    val id: Int,
    @Embedded
    var main: Main,
    val name: String,
    @Embedded
    var sys: Sys,
    val timezone: Int,
    val visibility: Int
)