package com.makuta.fiatconverter.api

import com.google.gson.JsonObject
import com.makuta.fiatconverter.CurrencyList
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("currencies.json")
    suspend fun list() : CurrencyList

    @GET("currencies/{name}.json")
    suspend fun convert(
        @Path("name") curr : String
    ) : JsonObject

}