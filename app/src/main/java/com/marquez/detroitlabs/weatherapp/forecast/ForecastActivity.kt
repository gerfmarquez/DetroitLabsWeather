package com.marquez.detroitlabs.weatherapp.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.marquez.detroitlabs.weatherapp.R
import com.marquez.detroitlabs.weatherapp.location.LocationData
import dagger.android.support.DaggerAppCompatActivity



class ForecastActivity : DaggerAppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
    }

    companion object {
        const val EXTRA_LOCATION = "EXTRA_LOCATION"

        fun newInstance(context: Context, locationData : LocationData): Intent {
            return Intent(context,ForecastActivity::class.java).apply {
                putExtra(EXTRA_LOCATION, locationData)
            }
        }
    }
}