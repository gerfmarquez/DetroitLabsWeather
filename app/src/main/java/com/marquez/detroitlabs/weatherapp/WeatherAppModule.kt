package com.marquez.detroitlabs.weatherapp

import com.marquez.detroitlabs.weatherapp.forecast.ForecastActivity
import com.marquez.detroitlabs.weatherapp.forecast.ForecastFragment

import com.marquez.detroitlabs.weatherapp.weather.WeatherActivity

import com.marquez.detroitlabs.weatherapp.weather.WeatherModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

@Module(includes = [ WeatherModule::class])
abstract class WeatherAppModule {

    @ContributesAndroidInjector
    abstract fun  bindWeatherActivity() : WeatherActivity

    @ContributesAndroidInjector ( )
    abstract fun  bindForecastActivity() : ForecastActivity

    @ContributesAndroidInjector
    abstract fun contributeForecastFragment() : ForecastFragment

}