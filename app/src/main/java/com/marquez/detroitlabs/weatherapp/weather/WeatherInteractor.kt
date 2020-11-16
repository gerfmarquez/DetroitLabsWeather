package com.marquez.detroitlabs.weatherapp.weather


import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import io.reactivex.Single


import javax.inject.Inject

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

class WeatherInteractor @Inject constructor(private val weatherRepository: WeatherRepository){

    fun fetchWeather(lat : Double, lon : Double)  : Single<WeatherResponse> {
        return weatherRepository.fetchWeather(lat,lon)
    }

}