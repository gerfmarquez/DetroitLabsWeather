package com.marquez.detroitlabs.weatherapp.model

data class City(
    val coord: CoordX,
    val country: String,
    val id: Int,
    val name: String,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)