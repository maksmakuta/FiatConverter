package com.makuta.fiatconverter.net

import com.makuta.fiatconverter.model.ConvertResponse
import com.makuta.fiatconverter.model.CurrenciesList
import retrofit2.http.GET
import retrofit2.http.Query

interface UniRateInterface {

    @GET("currencies")
    suspend fun getCurrencies() : CurrenciesList

    @GET("convert")
    suspend fun convert(
        @Query("from")      from : String,
        @Query("to")        to : String,
        @Query("amount")    amount : Double
    ) : ConvertResponse

}