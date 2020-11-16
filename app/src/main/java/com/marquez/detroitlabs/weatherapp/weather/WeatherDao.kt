package com.marquez.detroitlabs.weatherapp.weather;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import io.reactivex.Single

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

@Dao
interface WeatherDao {

    @Insert(onConflict = REPLACE)
    fun save(response: WeatherResponse)

    @Query("SELECT * FROM weatherresponse  order by  dt desc  limit 1")
    fun load( ) : Single<WeatherResponse>
}
