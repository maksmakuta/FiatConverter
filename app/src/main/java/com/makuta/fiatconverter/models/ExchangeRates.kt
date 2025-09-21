package com.makuta.fiatconverter.models

data class ExchangeRates(
    val timestamp : Long,
    val base : String,
    val rates : Map<String, Double>
)
