package com.marquez.detroitlabs.weatherapp.location



interface LocationView {
    fun updateLocation(location : LocationData)
    fun failedLocation()
}