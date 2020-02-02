package com.marquez.detroitlabs.weatherapp.weather

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.AlertDialog

import android.content.pm.PackageManager

import android.os.Bundle

import android.view.View

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.marquez.detroitlabs.weatherapp.R
import com.marquez.detroitlabs.weatherapp.forecast.ForecastActivity
import com.marquez.detroitlabs.weatherapp.location.LocationData
import com.marquez.detroitlabs.weatherapp.location.LocationPresenter
import com.marquez.detroitlabs.weatherapp.location.LocationView
import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.activity_weather.*

import javax.inject.Inject

class WeatherActivity : DaggerAppCompatActivity() , WeatherView, LocationView {

    @Inject lateinit var weatherPresenter: WeatherPresenter
    @Inject lateinit var locationPresenter : LocationPresenter

    private var locationData : LocationData? = null

    override fun onStart() {
        super.onStart()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            pg_loading.visibility = View.VISIBLE
            locationPresenter.fetchLocation()
        } else {
            requestPermissions( arrayOf(ACCESS_FINE_LOCATION),5)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        weatherPresenter.bindView(this)
        locationPresenter.bindView(this)
    }
    override fun onPause() {
        super.onPause()
        weatherPresenter.stopNetworkCall()
        locationPresenter.cleanup()
        bt_fiveday_forecast.visibility = View.GONE
    }

    override fun updateLocation(locationData: LocationData) {
        bt_fiveday_forecast.setOnClickListener {
            startActivity(ForecastActivity.newInstance(this,locationData))
        }
        weatherPresenter.fetchWeather(locationData.lat,locationData.lon)
        bt_fiveday_forecast.visibility = View.VISIBLE
    }
    override fun updateTemperature(weatherData : WeatherData) {
        pg_loading.visibility = View.GONE
        tv_forecast_temperature.text = weatherData.temperature?.toInt().toString()
        tv_city.text = weatherData.city
    }
    override fun failedLocation() {
        pg_loading.visibility = View.GONE
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Error Fetching Location").create().show()
        tv_forecast_temperature.text = "--"
    }
    override fun onTemperatureFailed() {
        pg_loading.visibility = View.GONE
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Error Fetching Temperature").create().show()
        tv_forecast_temperature.text = "--"
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPresenter.fetchLocation()
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)) {
                    finish()
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
