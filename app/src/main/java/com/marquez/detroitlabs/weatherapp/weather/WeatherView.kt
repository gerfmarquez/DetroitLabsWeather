package com.marquez.detroitlabs.weatherapp.weather

interface WeatherView {

    fun updateTemperature( temperature : Int)

    fun onTemperatureFailed()


}
