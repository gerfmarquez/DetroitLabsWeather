package com.marquez.detroitlabs.weatherapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

@Entity
data class Sys(
    val country: String,
    @PrimaryKey
    val sysId: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)