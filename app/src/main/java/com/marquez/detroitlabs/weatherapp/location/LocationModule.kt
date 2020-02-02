package com.marquez.detroitlabs.weatherapp.location


import com.marquez.detroitlabs.weatherapp.WeatherApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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