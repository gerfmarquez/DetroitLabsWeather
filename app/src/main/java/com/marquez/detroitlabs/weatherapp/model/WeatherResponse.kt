package com.marquez.detroitlabs.weatherapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marquez.detroitlabs.weatherapp.weather.WeatherData

@Entity
data class WeatherResponse(
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
   val visibility: Int) {
   constructor(weatherData : WeatherData) : this(0,
      Coord(0,0.0,0.0),0,0,
      Main(0.0,0,0,0,weatherData.temperature,0.0,0.0),weatherData.city,
      Sys("",0,0,0,0),0,0)
}