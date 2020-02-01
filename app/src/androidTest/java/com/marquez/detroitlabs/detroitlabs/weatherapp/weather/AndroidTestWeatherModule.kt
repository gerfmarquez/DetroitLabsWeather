package com.marquez.detroitlabs.detroitlabs.weatherapp.weather

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AndroidTestWeatherModule {
    @Provides @Singleton
    fun providesWeatherActivityTest() : WeatherActivityTest
}