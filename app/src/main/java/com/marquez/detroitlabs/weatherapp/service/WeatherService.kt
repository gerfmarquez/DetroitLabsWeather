package com.marquez.detroitlabs.weatherapp.service

import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import io.reactivex.Single

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    @GET("data/2.5/weather")
    fun fetchWeather(@Query("lat") lat: Double,
        @Query("lon") lon: Double) : Single<WeatherResponse>
//    @GET
//    fun fetchForecast(): Single<Object> {
//
//    }

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