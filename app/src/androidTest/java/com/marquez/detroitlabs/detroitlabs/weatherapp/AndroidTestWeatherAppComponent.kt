package com.marquez.detroitlabs.detroitlabs.weatherapp

import com.marquez.detroitlabs.weatherapp.WeatherApp
import com.marquez.detroitlabs.weatherapp.WeatherAppComponent
import com.marquez.detroitlabs.weatherapp.WeatherAppModule
import com.marquez.detroitlabs.weatherapp.weather.TestWeatherModule
import dagger.BindsInstance
import dagger.Component

@Component
interface AndroidTestWeatherAppComponent : WeatherAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: WeatherApp): Builder

        fun build(): WeatherAppComponent
    }

    override fun inject(app: WeatherApp)
}