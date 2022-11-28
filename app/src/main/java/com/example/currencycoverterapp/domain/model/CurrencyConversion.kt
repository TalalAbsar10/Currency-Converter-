package com.example.currencycoverterapp.domain.model

data class CurrencyConversion(
    val baseCode: String,
    val conversionRates: Map<String, Double>,
    val documentation: String,
    val result: String,
    val termsOfUse: String,
    val timeLastUpdateUnix: Int,
    val timeLastUpdateUtc: String,
    val timeNextUpdateUnix: Int,
    val timeNextUpdateUtc: String
)
