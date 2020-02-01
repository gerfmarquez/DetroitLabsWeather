package com.marquez.detroitlabs.weatherapp.service

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ParametersInterceptor(): Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest: Request = chain.request()
        val oldUrl: HttpUrl = oldRequest.url()

        val newUrl = oldUrl.newBuilder()
            .addQueryParameter("appid", WeatherService.apiKey)
            .addQueryParameter("units", "imperial")
            .build()

        val requestBuilder: Request.Builder = oldRequest.newBuilder()
            .url(newUrl)

        val newRequest: Request = requestBuilder.build()
        return chain.proceed(newRequest)
    }
}