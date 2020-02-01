package com.marquez.detroitlabs.weatherapp.location

import android.Manifest
import android.location.Location
import androidx.annotation.RequiresPermission
import io.reactivex.Single
import javax.inject.Inject

class LocationInteractor @Inject constructor(val locationProvider : LocationProvider){

    fun getLocation() : Single<LocationData> {
        return locationProvider.getLocation()
    }
}