package com.marquez.detroitlabs.weatherapp.weather

import com.marquez.detroitlabs.weatherapp.database.WeatherDatabase
import com.marquez.detroitlabs.weatherapp.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
class WeatherModule {
    @Singleton
    @Provides
    fun provideWeatherDao(db: WeatherDatabase) : WeatherDao {
        return db.weatherDao()
    }

    @Singleton
    @Provides
    fun provideWeatherRepo(weatherService: WeatherService, weatherDao : WeatherDao): WeatherRepository {
        return WeatherRepository(weatherService,weatherDao )
    }

    @Singleton
    @Provides
    fun provideWeatherInteractor(weatherRepository: WeatherRepository) : WeatherInteractor {
        return WeatherInteractor(weatherRepository )
    }


}