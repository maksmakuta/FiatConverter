package com.makuta.fiatconverter.api

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("country.json")
    suspend fun list() : JsonObject

    @GET("latest/currencies/{name}.json")
    suspend fun convert(
        @Path("name") curr : String
    ) : JsonObject

}