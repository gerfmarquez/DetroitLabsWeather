package com.marquez.detroitlabs.weatherapp.weather


import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import org.assertj.core.api.Assertions
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.ArgumentMatchers.*
import org.mockito.InjectMocks


import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.assertj.core.api.Java6Assertions.assertThat
import org.mockito.junit.MockitoRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class WeatherPresenterTest {

    @Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var  weatherInteractor: WeatherInteractor
    @Mock
    lateinit var  weatherView: WeatherView
    @InjectMocks
    lateinit var weatherPresenter: WeatherPresenter
    @InjectMocks
    lateinit var weatherRepository : WeatherRepository

    @Before
    fun setup() {

    }

    @Test
    fun presenterTestSuccessful() {
        weatherPresenter.bindView(weatherView)

//        Mockito.`when`(weatherInteractor.fetchWeather())

        weatherPresenter.fetchWeather(1234.1234,1234.1234)

        Mockito.verify(weatherView).updateTemperature(Mockito.anyInt())

//        weatherInteractor.fetchWeather()

        val weatherResponse =  weatherRepository.fetchWeather(1.5,1.5)

//        assertThat(weatherResponse).containsExactly(
//            WeatherResponse(0,null,0,0,null,"",null,0,0)
//        )

    }
    @Test
    fun presenterTestFailure() {

    }
}

