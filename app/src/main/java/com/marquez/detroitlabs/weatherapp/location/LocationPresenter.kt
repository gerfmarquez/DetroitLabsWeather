package com.marquez.detroitlabs.weatherapp.location

import android.Manifest
import android.util.Log
import androidx.annotation.RequiresPermission
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationPresenter @Inject constructor (val locationInteractor: LocationInteractor) {

    var disposable : Disposable? = null
    var locationView : LocationView? = null

    fun bindView( view : LocationView) {
        locationView = view
    }

    fun fetchLocation() {
        disposable = locationInteractor.getLocation()
        .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {   location ->
                    locationView?.updateLocation(location)
                },
                {   failure ->
                    locationView?.failedLocation()
                }
            )
    }
    fun cleanup() {
        if(disposable != null && disposable?.isDisposed == false)
            disposable?.dispose()
    }
}