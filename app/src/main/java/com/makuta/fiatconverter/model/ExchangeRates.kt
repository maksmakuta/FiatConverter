package com.makuta.fiatconverter.model

data class ExchangeRates(
    val timestamp : Long,
    val base : String,
    val rates : Map<String, Double>
)
