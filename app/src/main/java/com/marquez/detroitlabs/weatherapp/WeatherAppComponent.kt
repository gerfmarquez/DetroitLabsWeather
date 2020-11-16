package com.marquez.detroitlabs.weatherapp


import com.marquez.detroitlabs.weatherapp.location.LocationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule


import dagger.android.AndroidInjector

import javax.inject.Singleton

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

@Singleton
@Component( modules = [AndroidInjectionModule::class,WeatherAppModule::class,  LocationModule::class])
interface WeatherAppComponent : AndroidInjector<WeatherApp> {

        @Component.Builder
        interface Builder {
                @BindsInstance
                fun application(application: WeatherApp): Builder

                fun build(): WeatherAppComponent
        }

        override fun inject(app: WeatherApp)

}