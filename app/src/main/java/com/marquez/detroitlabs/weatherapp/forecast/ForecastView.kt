package com.marquez.detroitlabs.weatherapp.forecast

interface ForecastView {
    fun forecastResults(forecastResults : List<ForecastData>)
    fun forecastFailed()
}
