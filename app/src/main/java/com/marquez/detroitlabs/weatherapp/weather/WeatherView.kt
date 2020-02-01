package com.marquez.detroitlabs.weatherapp.weather


interface WeatherView {

    fun updateTemperature( city : String, temperature : String)

    fun onTemperatureFailed()


}
