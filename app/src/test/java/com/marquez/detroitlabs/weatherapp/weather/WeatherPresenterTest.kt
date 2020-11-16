package com.marquez.detroitlabs.weatherapp.weather


import com.marquez.detroitlabs.weatherapp.RxImmediateSchedulerRule

import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import io.reactivex.Single
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test

import org.mockito.InjectMocks
import org.mockito.Mock

import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

class WeatherPresenterTest {
    @get:Rule  val mockitoRule: MockitoRule = MockitoJUnit.rule()
    companion object {
        @ClassRule @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }
    @Mock
    lateinit var  weatherInteractor: WeatherInteractor
    @Mock
    lateinit var  weatherView: WeatherView

    @InjectMocks
    lateinit var weatherPresenter: WeatherPresenter

    @Test
    fun presenterTestSuccessful() {
        val weatherData  = WeatherData("Ann Arbor", 25.5)
        val weatherResponse = WeatherResponse(weatherData)

        `when`(weatherInteractor.fetchWeather(1.5,1.5))
            .thenReturn(Single.just(weatherResponse))

        weatherPresenter.bindView(weatherView)
        weatherPresenter.fetchWeather(1.5,1.5)


        verify(weatherView).updateTemperature(weatherData)


    }
}

