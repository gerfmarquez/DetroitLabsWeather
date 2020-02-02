package com.marquez.detroitlabs.weatherapp.forecast

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastData ( val dateTime : Long, val temperature : Long, val icon : String) : Parcelable
