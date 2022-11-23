package com.example.currencyconverterapp.data.remote.dto

import com.example.currencycoverterapp.domain.model.CurrencyConversion
import com.google.gson.annotations.SerializedName

data class CurrencyConverisonDTO(
    @SerializedName("base_code")
    val baseCode: String,
    @SerializedName("conversion_rates")
    val conversionRates: ConversionRates,
    val documentation: String,
    val result: String,
    @SerializedName("terms_of_use")
    val termsOfUse: String,
    @SerializedName("time_last_update_unix")
    val timeLastUpdateUnix: Int,
    @SerializedName("time_last_update_utc")
    val timeLastUpdateUtc: String,
    @SerializedName("time_next_update_unix")
    val timeNextUpdateUnix: Int,
    @SerializedName("time_next_update_utc")
    val timeNextUpdateUtc: String
)

fun CurrencyConverisonDTO.toCurrencies(): CurrencyConversion {
    return CurrencyConversion(
        baseCode = baseCode,
        conversionRates = conversionRates,
        result = result
    )
}
