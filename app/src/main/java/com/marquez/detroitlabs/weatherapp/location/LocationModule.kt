package com.marquez.detroitlabs.weatherapp.location


import com.marquez.detroitlabs.weatherapp.WeatherApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

@Module
class LocationModule {
    @Singleton
    @Provides
    fun provideLocationInteractor(locationProvider: LocationProvider) : LocationInteractor {
        return LocationInteractor(locationProvider )
    }
    @Singleton
    @Provides
    fun provideLocationProvider(weatherApp : WeatherApp): LocationProvider {
        return LocationProvider(weatherApp )
    }
}