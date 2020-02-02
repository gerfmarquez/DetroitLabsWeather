package com.marquez.detroitlabs.weatherapp.weather

import com.marquez.detroitlabs.weatherapp.RxImmediateSchedulerRule
import com.marquez.detroitlabs.weatherapp.model.WeatherResponse
import com.marquez.detroitlabs.weatherapp.service.WeatherService
import io.reactivex.Single
import org.junit.*
import org.junit.Assert.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class WeatherRepositoryTest  {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()
    @Mock
    lateinit var weatherService: WeatherService
    @Mock
    lateinit var weatherDao : WeatherDao
    @InjectMocks
    lateinit var weatherRepository : WeatherRepository

    companion object {
        val dummyDouble : Double = 1.5
        val weatherData  = WeatherData("Ann Arbor", 25.5)
        val weatherResponse = WeatherResponse(weatherData)
        val  smallOffset = 30 * 1000 //30 seconds offset

        @ClassRule@JvmField val schedulers = RxImmediateSchedulerRule()
    }

    @Test
    /**1. Test first repository call to fetch from network**/
    fun testRepositoryNetwork() {
        `when`(weatherService.fetchWeather(dummyDouble, dummyDouble)).thenReturn(Single.just(weatherResponse))

        var singleResponse: Single<WeatherResponse> = weatherRepository.fetchWeather(dummyDouble, dummyDouble)

        verify(weatherService, times(1)).fetchWeather(dummyDouble, dummyDouble)

        var repositoryResponse  = singleResponse.blockingGet()
        assertEquals(repositoryResponse?.main?.temp, 25.5)
        assertEquals(repositoryResponse?.name, "Ann Arbor")
    }
    @Test
    /**2. Test repository call to fetch from database**/
    fun testRepositoryDatabaseCache() {
        //simulate behind of  time to allow using of cache
        val smallOffset = 10 * 1000 //10 seconds offset
        weatherRepository.timestamp = System.currentTimeMillis()
        weatherRepository.timestamp += (WeatherRepository.cache_memory_ms + smallOffset)

        `when`(weatherDao.load()).thenReturn(Single.just(weatherResponse))
        val singleResponse = weatherRepository.fetchWeather(dummyDouble, dummyDouble)

        verify(weatherDao, times(2)).load()
        verify(weatherService, times(0)).fetchWeather(dummyDouble, dummyDouble)

        val repositoryResponse  = singleResponse.blockingGet()
        assertEquals(repositoryResponse?.main?.temp, 25.5)
        assertEquals(repositoryResponse?.name, "Ann Arbor")
    }

    @Test
    /**3. Test repository call to fetch from memory**/
    fun testRepositoryMemoryCache() {
        //simulate behind of  time to allow using cache
        weatherRepository.timestamp = System.currentTimeMillis()
        weatherRepository.timestamp += (WeatherRepository.cache_memory_ms + smallOffset)

        weatherRepository.weatherResponseMemoryCache = weatherResponse

        val singleResponse = weatherRepository.fetchWeather(dummyDouble, dummyDouble)

        verify(weatherService, times(0)).fetchWeather(dummyDouble, dummyDouble)

        val repositoryResponse  = singleResponse.blockingGet()
        assertEquals(repositoryResponse?.main?.temp, 25.5)
        assertEquals(repositoryResponse?.name, "Ann Arbor")
    }
    @Test
    /**4. Test repository expired memory cache**/
    fun testRepositoryExpiredMemoryCache() {

        //simulate ahead of time to allow refreshing of cache
        weatherRepository.timestamp = System.currentTimeMillis()
        weatherRepository.timestamp -= (WeatherRepository.cache_memory_ms + smallOffset)

        `when`(weatherDao.load()).thenReturn(Single.just(weatherResponse))

        val singleResponse = weatherRepository.fetchWeather(dummyDouble, dummyDouble)
        //fallback to database
        verify(weatherDao, times(2)).load()

        val repositoryResponse  = singleResponse.blockingGet()
        assertEquals(repositoryResponse?.main?.temp, 25.5)
        assertEquals(repositoryResponse?.name, "Ann Arbor")
    }

    @Test
    /** Test repository expired database cache **/
    fun testRepositoryExpiredDatabaseCache() {
        //simulate ahead of time to allow refreshing of cache
        weatherRepository.timestamp = System.currentTimeMillis()
        weatherRepository.timestamp -= (WeatherRepository.cache_database_ms  + smallOffset)

        `when`(weatherService.fetchWeather(dummyDouble, dummyDouble)).thenReturn(Single.just(weatherResponse))

        val singleResponse = weatherRepository.fetchWeather(dummyDouble, dummyDouble)

        verify(weatherService, times(1)).fetchWeather(dummyDouble, dummyDouble)

        val repositoryResponse = singleResponse.blockingGet()
        assertEquals(repositoryResponse?.main?.temp, 25.5)
        assertEquals(repositoryResponse?.name, "Ann Arbor")
    }


}