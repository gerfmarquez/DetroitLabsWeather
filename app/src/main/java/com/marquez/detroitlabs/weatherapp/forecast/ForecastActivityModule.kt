package com.marquez.detroitlabs.weatherapp.forecast

import com.marquez.detroitlabs.weatherapp.weather.WeatherActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ForecastActivityModule {

    @ContributesAndroidInjector ( modules = [FragmentBuildersModule::class])
    abstract fun  bindForecastActivity() : ForecastActivity

}