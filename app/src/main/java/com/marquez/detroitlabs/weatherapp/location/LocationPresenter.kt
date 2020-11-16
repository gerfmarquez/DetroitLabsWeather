package com.marquez.detroitlabs.weatherapp.location

import android.Manifest
import android.util.Log
import androidx.annotation.RequiresPermission
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

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