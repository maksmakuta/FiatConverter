package com.makuta.fiatconverter.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UniRateInterface::class.java)
    }

}