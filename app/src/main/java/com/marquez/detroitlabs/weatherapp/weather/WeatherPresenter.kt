package com.marquez.detroitlabs.weatherapp.weather

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherPresenter @Inject constructor (private val weatherInteractor: WeatherInteractor) {
     var weatherView : WeatherView? = null
    var rxDisposable : Disposable? = null

    fun bindView ( view: WeatherView) {
        weatherView = view
    }

    fun fetchWeather(lat: Double, lon : Double)  {
        rxDisposable = weatherInteractor.fetchWeather(lat, lon) .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { successWeatherResponse ->
                    weatherView?.updateTemperature(
                        successWeatherResponse.name,
                        successWeatherResponse?.main?.temp?.toInt().toString())
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