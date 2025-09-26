package com.makuta.fiatconverter.model

data class ConvertResponse(
    val amount: Double,
    val from: String,
    val result: Double,
    val to: String
)
