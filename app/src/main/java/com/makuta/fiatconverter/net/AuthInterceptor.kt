package com.makuta.fiatconverter.net

import com.makuta.fiatconverter.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Token ${BuildConfig.API_KEY}")
            .build()
        return chain.proceed(newRequest)
    }
}
