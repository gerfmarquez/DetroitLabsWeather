package com.marquez.detroitlabs.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marquez.detroitlabs.weatherapp.model.Sys
import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import com.marquez.detroitlabs.weatherapp.weather.WeatherDao

@Database(entities = [WeatherResponse::class, Sys::class],  version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao

}