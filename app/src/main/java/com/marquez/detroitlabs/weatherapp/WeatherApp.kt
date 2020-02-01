package com.marquez.detroitlabs.weatherapp

import dagger.android.DaggerApplication

class WeatherApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun applicationInjector() = DaggerWeatherAppComponent.builder()
        .application(this)
        .build()

}