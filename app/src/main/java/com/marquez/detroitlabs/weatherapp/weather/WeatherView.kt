package com.marquez.detroitlabs.weatherapp.weather


interface WeatherView {

    fun updateTemperature( weatherData : WeatherData)

    fun onTemperatureFailed()


}
