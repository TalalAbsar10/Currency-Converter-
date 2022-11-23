package com.example.currencycoverterapp.domain.model

import com.example.currencyconverterapp.data.remote.dto.ConversionRates

data class CurrencyConversion(
    val result: String,
    val baseCode: String,
    val conversionRates: ConversionRates
)
