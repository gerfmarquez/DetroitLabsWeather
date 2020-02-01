package com.marquez.detroitlabs.weatherapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
data class Sys(
    val country: String,
    @PrimaryKey
    val sysId: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)