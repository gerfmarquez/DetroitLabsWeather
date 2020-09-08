package com.marquez.detroitlabs.weatherapp.location

import com.marquez.detroitlabs.weatherapp.RxImmediateSchedulerRule
import io.reactivex.Single
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class LocationPresenterTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    lateinit var  locationView: LocationView
    @Mock
    lateinit var locationInteractor: LocationInteractor

    @InjectMocks
    lateinit var locationPresenter: LocationPresenter

    @Test
    fun presenterTestSuccessful() {
        val locationData  = LocationData(1,1.5,1.5)

        Mockito.`when`(locationInteractor.getLocation())
            .thenReturn(Single.just(locationData))

        locationPresenter.bindView(locationView)
        locationPresenter.fetchLocation()

        verify(locationView).updateLocation(locationData)


    }
}