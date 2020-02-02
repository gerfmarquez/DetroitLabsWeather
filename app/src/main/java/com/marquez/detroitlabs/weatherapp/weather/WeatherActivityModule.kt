package com.marquez.detroitlabs.weatherapp.weather

import com.marquez.detroitlabs.weatherapp.weather.WeatherActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherActivityModule {

    @ContributesAndroidInjector
    abstract fun  bindWeatherActivity() : WeatherActivity
}