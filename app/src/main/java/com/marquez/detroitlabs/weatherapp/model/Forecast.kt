package com.marquez.detroitlabs.weatherapp.model

import androidx.room.Embedded
import androidx.room.Entity

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

@Entity
data class Forecast(
    @Embedded
    var city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastDetail>,
    val message: Int
)