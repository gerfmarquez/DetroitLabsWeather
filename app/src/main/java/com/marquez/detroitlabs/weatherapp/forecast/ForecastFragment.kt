package com.marquez.detroitlabs.weatherapp.forecast

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.marquez.detroitlabs.weatherapp.R
import com.marquez.detroitlabs.weatherapp.location.LocationData
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.forecast_view.view.*
import javax.inject.Inject

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

class ForecastFragment  : DaggerFragment() , ForecastView {
    @Inject
    lateinit var forecastPresenter : ForecastPresenter
    var forecastAdapter: ForecastAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container:  ViewGroup?,
                              savedInstanceState: Bundle?): View {

        forecastPresenter.bindView(this)
        val locationData : LocationData? = activity?.intent?.getParcelableExtra(ForecastActivity.EXTRA_LOCATION)
        if(locationData != null) {
            forecastPresenter.fetchForecast(locationData)
        }
        val layout : LinearLayout = inflater.inflate(R.layout.forecast_view,   container,  false) as LinearLayout

        val rvAdapter = ForecastAdapter(context!!)

        forecastAdapter = rvAdapter
        layout.rv_forecast.adapter = forecastAdapter

        return layout
    }

    override fun forecastResults(forecastResults: List<ForecastData>) {
        forecastAdapter?.submitList(forecastResults)
    }
    override fun forecastFailed() {
        val builder = AlertDialog.Builder(this.context)
        builder.setMessage("Error Fetching Forecast").create().show()
    }

    override fun onPause() {
        super.onPause()
        forecastPresenter.cleanup()
    }
}