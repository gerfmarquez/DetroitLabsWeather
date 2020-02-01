package com.marquez.detroitlabs.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coord(
    @PrimaryKey(autoGenerate = true)
    val coordId: Int,
    val lat: Double,
    val lon: Double
)