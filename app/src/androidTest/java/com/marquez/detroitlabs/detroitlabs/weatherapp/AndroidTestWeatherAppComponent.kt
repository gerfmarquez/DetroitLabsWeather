package com.marquez.detroitlabs.detroitlabs.weatherapp

import com.marquez.detroitlabs.weatherapp.WeatherApp
import com.marquez.detroitlabs.weatherapp.WeatherAppComponent
import com.marquez.detroitlabs.weatherapp.WeatherAppModule
import com.marquez.detroitlabs.weatherapp.weather.TestWeatherModule
import dagger.BindsInstance
import dagger.Component

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

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