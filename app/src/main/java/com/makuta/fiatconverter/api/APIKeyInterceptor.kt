package com.makuta.fiatconverter.api

import com.makuta.fiatconverter.*
import okhttp3.Interceptor
import okhttp3.Response

class APIKeyInterceptor  : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val newRequest = original.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

}