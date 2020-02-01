package com.marquez.detroitlabs.weatherapp.weather;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import io.reactivex.Single

@Dao
interface WeatherDao {

    @Insert(onConflict = REPLACE)
    fun save(response: WeatherResponse)

    @Query("SELECT * FROM weatherresponse  order by  dt desc  limit 1")
    fun load( ) : Single<WeatherResponse>
}
