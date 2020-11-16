package com.marquez.detroitlabs.weatherapp.model

import androidx.room.Embedded

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

data class ForecastDetail (
    @Embedded
    var clouds: Clouds,
    val dt: Long,
    val dt_txt: String,
    @Embedded
    var main: ForecastRow,
    @Embedded
    var sys: SysX,
    val weather: List<Weather>,
    @Embedded
    var wind: Wind
)