package com.marquez.detroitlabs.weatherapp.weather


import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import io.reactivex.Single


import javax.inject.Inject

class WeatherInteractor @Inject constructor(private val weatherRepository: WeatherRepository){

    fun fetchWeather(lat : Double, lon : Double)  : Single<WeatherResponse> {
        return weatherRepository.fetchWeather(lat,lon)
    }

}