package com.marquez.detroitlabs.weatherapp.weather


import com.marquez.detroitlabs.weatherapp.RxImmediateSchedulerRule
import com.marquez.detroitlabs.weatherapp.model.Main
import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import io.reactivex.Single
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import javax.inject.Inject


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class WeatherPresenterTest {
    @get:Rule  val mockitoRule: MockitoRule = MockitoJUnit.rule()
//    @get:Rule  val asyncTaskSchedulerRule : AsyncTaskSchedulerRule = AsyncTaskSchedulerRule()
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
//    @InjectMocks
//    lateinit var weatherRepository : WeatherRepository

    @Test
    fun presenterTestSuccessful() {
        val weatherData  = WeatherData("Ann Arbor", 25.5)
        val weatherResponse = WeatherResponse(weatherData)

        `when`(weatherInteractor.fetchWeather(1.5,1.5))
            .thenReturn(Single.just(weatherResponse))

        weatherPresenter.bindView(weatherView)
        weatherPresenter.fetchWeather(1.5,1.5)


        verify(weatherView).updateTemperature(
            weatherData.city,   weatherData.temperature.toInt().toString())


    }
    @Test
    fun presenterTestFailure() {

    }
}

