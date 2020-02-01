package com.marquez.detroitlabs.weatherapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
data class Coord(
    @PrimaryKey(autoGenerate = true)
    val coordId: Int,
    val lat: Double,
    val lon: Double
)