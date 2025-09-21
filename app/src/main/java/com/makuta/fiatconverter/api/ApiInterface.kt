package com.makuta.fiatconverter.api

import com.makuta.fiatconverter.models.ExchangeRates
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiInterface {

    @GET("latest.json")
    fun getRates() : Flow<ExchangeRates>


}