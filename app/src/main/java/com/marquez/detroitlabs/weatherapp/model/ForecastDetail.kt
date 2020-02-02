package com.marquez.detroitlabs.weatherapp.model

import androidx.room.Embedded

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