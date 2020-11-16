package com.marquez.detroitlabs.weatherapp.weather

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

class WeatherPresenter @Inject constructor (private val weatherInteractor: WeatherInteractor) {
     var weatherView : WeatherView? = null
    var rxDisposable : Disposable? = null

    fun bindView ( view: WeatherView) {
        weatherView = view
    }

    fun fetchWeather(lat: Double, lon : Double)  {
        rxDisposable = weatherInteractor.fetchWeather(lat, lon) .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { weatherData ->
                    weatherView?.updateTemperature(
                        WeatherData(weatherData.name,weatherData.main.temp))
            },
            {
                throwable ->
                weatherView?.onTemperatureFailed()
            }
            )
    }

    fun stopNetworkCall() {
        if (rxDisposable != null  &&  rxDisposable?.isDisposed == false) {
            rxDisposable?.dispose()
        }
    }
}