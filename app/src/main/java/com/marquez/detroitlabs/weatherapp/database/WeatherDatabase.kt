package com.marquez.detroitlabs.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marquez.detroitlabs.weatherapp.model.Sys
import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import com.marquez.detroitlabs.weatherapp.weather.WeatherDao

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

@Database(entities = [WeatherResponse::class, Sys::class],  version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao

}