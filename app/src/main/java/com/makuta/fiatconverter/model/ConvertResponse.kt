package com.makuta.fiatconverter.model

data class ConvertResponse(
    val amount: Double,
    val from: String,
    var result: Double,
    val to: String
)
