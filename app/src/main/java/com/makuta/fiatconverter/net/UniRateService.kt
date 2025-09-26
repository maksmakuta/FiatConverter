package com.makuta.fiatconverter.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class UniRateService {

    companion object{
        private const val BASE_URL = "https://unirateapi.com/api/"
    }

    fun provideService() : UniRateInterface{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(UniRateInterface::class.java)
    }

}