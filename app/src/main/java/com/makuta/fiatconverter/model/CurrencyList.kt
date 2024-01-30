package com.makuta.fiatconverter.model

data class CurrencyList(
    val error : Int,
    val error_message : String,
    val currencies : List<Currency>
)
