package com.marquez.detroitlabs.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sys(
    val country: String,
    @PrimaryKey
    val sysId: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)