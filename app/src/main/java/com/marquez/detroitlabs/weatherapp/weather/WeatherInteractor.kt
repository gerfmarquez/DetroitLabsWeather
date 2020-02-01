package com.marquez.detroitlabs.weatherapp.weather



import android.service.autofill.Validators.not
import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


import javax.inject.Inject

open class WeatherInteractor @Inject constructor(private val weatherRepository: WeatherRepository){

    fun fetchWeather(lat : Double, lon : Double)  : Single<WeatherResponse> {
        return weatherRepository.fetchWeather(lat,lon)
    }

}