package com.marquez.detroitlabs.weatherapp.weather

import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import com.marquez.detroitlabs.weatherapp.service.WeatherService
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


import javax.inject.Inject

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

class WeatherRepository @Inject constructor (
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao
) {
    companion object {
        val cache_memory_ms : Int = 5 * 60 * 1000 //5 minutes cache data
        val cache_database_ms : Int = 15 * 60 * 1000 //5 minutes cache data
    }
    var timestamp : Long = 1
    var weatherResponseMemoryCache : WeatherResponse? = null
    var disposable : Disposable? = null

    fun fetchWeather(lat: Double, lon: Double): Single<WeatherResponse> {
        if(isMemoryCacheLatest() && weatherResponseMemoryCache != null) {
            return Single.just(weatherResponseMemoryCache)
        } else if ( isDatabaseCacheLatest() && isDatabaseCached()) {
            return weatherDao.load()
        } else {
            val single = weatherService.fetchWeather(lat,lon)
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

    private fun isMemoryCacheLatest() : Boolean {
        return System.currentTimeMillis()   <  timestamp  + cache_memory_ms
    }
    private fun isDatabaseCacheLatest() : Boolean {
        return System.currentTimeMillis()   <   timestamp + cache_database_ms
    }
    private fun isDatabaseCached() : Boolean {
       return   weatherDao.load().blockingGet() != null
    }
}