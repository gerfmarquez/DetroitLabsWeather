package com.marquez.detroitlabs.weatherapp.weather

import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import com.marquez.detroitlabs.weatherapp.service.WeatherService
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


import javax.inject.Inject

class WeatherRepository @Inject constructor (
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao
) {
    val cache_memory_ms : Int = 5 * 60 * 1000 //5 minutes cache data
    val cache_database_ms : Int = 15 * 60 * 1000 //5 minutes cache data
    var timestamp : Long = 1
    var weatherResponseMemoryCache : WeatherResponse? = null
    var disposable : Disposable? = null

    fun fetchWeather(lat: Double, lon: Double): Single<WeatherResponse> {
        if(isMemoryCacheLatest() && weatherResponseMemoryCache != null) {
            return Single.just(weatherResponseMemoryCache)
        } else if (isDatabaseCacheLatest()) {
            return weatherDao.load()
        } else {
            val single = fetchWeatherFromNetwork(lat,lon)
            disposable  = single .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).subscribe(
            {
            success ->
                weatherResponseMemoryCache = success
                weatherDao.save(success)
                timestamp = System.currentTimeMillis()
                disposable?.dispose()
            },
            {  /**NOP **/ })
            return single
        }
    }

    fun fetchWeatherFromNetwork(lat: Double, lon: Double) : Single<WeatherResponse> {
        return weatherService.fetchWeather(lat,lon)
    }
    private fun isMemoryCacheLatest() : Boolean{
        return (timestamp + cache_memory_ms ) > System.currentTimeMillis()
    }
    private fun isDatabaseCacheLatest() : Boolean{
        return (timestamp + cache_database_ms ) > System.currentTimeMillis()
    }
}