package com.marquez.detroitlabs.weatherapp.location


import io.reactivex.Single
import javax.inject.Inject

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

class LocationInteractor @Inject constructor(val locationProvider : LocationProvider){

    fun getLocation() : Single<LocationData> {
        return locationProvider.getLocation()
    }
}