package com.marquez.detroitlabs.weatherapp.service

import com.marquez.detroitlabs.weatherapp.model.Forecast
import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import io.reactivex.Single

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

interface WeatherService {

    @GET("data/2.5/weather")
    fun fetchWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double) : Single<WeatherResponse>

    @GET("data/2.5/forecast")
    fun fetchForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double) : Single<Forecast>

    companion object  {
        val apiKey: String = "4343d559e3b708fedb53edc03a332c75"
        val baseUrl: String = "https://api.openweathermap.org/"
        fun create(): WeatherService {

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(ParametersInterceptor())

            return  Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

                .client(httpClient.build())
                .build().create(WeatherService::class.java)
        }
    }
}