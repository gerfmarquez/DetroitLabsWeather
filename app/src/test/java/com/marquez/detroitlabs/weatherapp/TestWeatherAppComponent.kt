package com.marquez.detroitlabs.weatherapp

import com.marquez.detroitlabs.weatherapp.weather.TestWeatherModule
import com.marquez.detroitlabs.weatherapp.weather.WeatherModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class, TestWeatherModule::class, WeatherModule::class])
interface TestWeatherAppComponent : WeatherAppComponent
