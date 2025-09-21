package com.makuta.fiatconverter.net

import com.makuta.fiatconverter.model.ExchangeRates
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("latest.json")
    fun getRates(
        @Query("base")
        base : String = "USD",

        @Query("symbols")
        symbols : List<String>,

        @Query("prettyprint")
        pretty : Boolean = false
    ) : ExchangeRates


}