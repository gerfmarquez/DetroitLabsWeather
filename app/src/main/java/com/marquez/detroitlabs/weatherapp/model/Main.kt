package com.marquez.detroitlabs.weatherapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    @PrimaryKey(autoGenerate = true)
    var mainId: Int,
    var temp: Double,
    val temp_max: Double,
    val temp_min: Double
)