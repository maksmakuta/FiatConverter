package com.makuta.fiatconverter.net

import com.makuta.fiatconverter.model.ConvertResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UniRateInterface {

    @GET("convert")
    suspend fun convert(
        @Query("from")      from : String,
        @Query("to")        to : String,
        @Query("amount")    amount : Double
    ) : ConvertResponse

}