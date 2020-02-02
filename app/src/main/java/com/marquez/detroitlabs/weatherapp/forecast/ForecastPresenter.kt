package com.marquez.detroitlabs.weatherapp.forecast

import com.marquez.detroitlabs.weatherapp.location.LocationData
import com.marquez.detroitlabs.weatherapp.service.WeatherService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ForecastPresenter @Inject constructor ( private val weatherService: WeatherService ) {

    var forecastView : ForecastView? = null
    var rxDisposable : Disposable? = null

    fun bindView ( view: ForecastView) {
        forecastView = view
    }

    fun fetchForecast( locationData : LocationData)  {
        rxDisposable = weatherService.fetchForecast(locationData.lat,locationData.lon) .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { forecastData ->
                forecastView?.forecastResults(
                    forecastData.list.map {
                        ForecastData(it.dt, it.main.temp.toLong(),it.weather.first().icon)
                    })
            },
            {   throwable ->
                throwable.printStackTrace()
                forecastView?.forecastFailed()
            } )
    }

    fun cleanup() {
        if (rxDisposable != null  &&  rxDisposable?.isDisposed == false) {
            rxDisposable?.dispose()
        }
    }
}

