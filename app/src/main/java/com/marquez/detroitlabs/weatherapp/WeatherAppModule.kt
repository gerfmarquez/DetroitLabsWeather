package com.marquez.detroitlabs.weatherapp

import androidx.room.Room
import com.marquez.detroitlabs.weatherapp.database.WeatherDatabase
import com.marquez.detroitlabs.weatherapp.forecast.ForecastActivity
import com.marquez.detroitlabs.weatherapp.forecast.ForecastActivityModule
import com.marquez.detroitlabs.weatherapp.forecast.ForecastFragment
import com.marquez.detroitlabs.weatherapp.forecast.FragmentBuildersModule

import com.marquez.detroitlabs.weatherapp.service.WeatherService
import com.marquez.detroitlabs.weatherapp.weather.WeatherActivity
import com.marquez.detroitlabs.weatherapp.weather.WeatherActivityModule

import com.marquez.detroitlabs.weatherapp.weather.WeatherModule

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

import javax.inject.Singleton
@Module(includes = [ WeatherModule::class])
abstract class WeatherAppModule {

    @ContributesAndroidInjector
    abstract fun  bindWeatherActivity() : WeatherActivity

    @ContributesAndroidInjector ( )
    abstract fun  bindForecastActivity() : ForecastActivity

    @ContributesAndroidInjector
    abstract fun contributeForecastFragment() : ForecastFragment

}