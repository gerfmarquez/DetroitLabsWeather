package com.marquez.detroitlabs.weatherapp.location

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import com.marquez.detroitlabs.weatherapp.WeatherApp
import io.reactivex.Single
import javax.inject.Inject

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

class  LocationProvider @Inject constructor(val weatherApp: WeatherApp){

    fun getLocation() : Single<LocationData> {
        return Single.fromPublisher<LocationData> {
            val locationManager = weatherApp.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            try {
                val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if(location != null )  it.onNext(LocationData(location.time, location.latitude, location.longitude))
                it.onComplete()
            } catch(securityException : SecurityException) {
                securityException.printStackTrace(System.err)
                it.onError(securityException)
            }
        }
    }
}