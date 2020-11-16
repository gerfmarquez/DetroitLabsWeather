package com.marquez.detroitlabs.weatherapp.forecast

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

@Parcelize
data class ForecastData ( val dateTime : Long, val temperature : Long, val icon : String) : Parcelable
