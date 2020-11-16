package com.marquez.detroitlabs.weatherapp.service

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/** This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Copyright 2020, Gerardo Marquez.
 */

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