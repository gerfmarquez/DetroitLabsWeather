package com.marquez.detroitlabs.weatherapp

import androidx.room.Room
import com.marquez.detroitlabs.weatherapp.database.WeatherDatabase
import com.marquez.detroitlabs.weatherapp.forecast.ForecastActivityModule

import com.marquez.detroitlabs.weatherapp.service.WeatherService
import com.marquez.detroitlabs.weatherapp.weather.WeatherActivityModule

import com.marquez.detroitlabs.weatherapp.weather.WeatherModule

import dagger.Module
import dagger.Provides

import javax.inject.Singleton
@Module(includes = [WeatherActivityModule::class, WeatherModule::class, ForecastActivityModule::class])
class WeatherAppModule {
    @Singleton
    @Provides
    fun provideWeatherService(): WeatherService {
        return WeatherService.create()
    }

    @Singleton
    @Provides
    fun provideWeatherDb(app: WeatherApp) : WeatherDatabase {
        return Room.databaseBuilder(app, WeatherDatabase::class.java, "WeatherDatabase.db")
            .allowMainThreadQueries()
        .fallbackToDestructiveMigration().build()
    }



}