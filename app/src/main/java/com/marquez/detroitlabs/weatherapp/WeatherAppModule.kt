package com.marquez.detroitlabs.weatherapp

import androidx.room.Room
import com.marquez.detroitlabs.weatherapp.database.WeatherDatabase

import com.marquez.detroitlabs.weatherapp.service.WeatherService
import com.marquez.detroitlabs.weatherapp.weather.WeatherDao
import com.marquez.detroitlabs.weatherapp.weather.WeatherInteractor
import com.marquez.detroitlabs.weatherapp.weather.WeatherModule
import com.marquez.detroitlabs.weatherapp.weather.WeatherRepository
import dagger.Module
import dagger.Provides

import javax.inject.Singleton
@Module(includes = [WeatherActivityModule::class, WeatherModule::class])
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
        .fallbackToDestructiveMigration().build()
    }



}