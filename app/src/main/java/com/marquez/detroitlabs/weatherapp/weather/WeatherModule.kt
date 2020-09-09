package com.marquez.detroitlabs.weatherapp.weather

import androidx.room.Room
import com.marquez.detroitlabs.weatherapp.WeatherApp
import com.marquez.detroitlabs.weatherapp.database.WeatherDatabase
import com.marquez.detroitlabs.weatherapp.service.WeatherService
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class WeatherModule {

    @Singleton
    @Provides
    fun provideWeatherService(): WeatherService {
        return WeatherService.create()
    }

    @Singleton
    @Provides
    fun provideWeatherDb(app: WeatherApp) : WeatherDatabase {
        return Room.databaseBuilder(app, WeatherDatabase::class.java, "WeatherDatabase.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()
    }

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