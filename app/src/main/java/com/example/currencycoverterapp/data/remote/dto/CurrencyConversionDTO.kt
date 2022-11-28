package com.example.currencycoverterapp.data.remote.dto

import com.example.currencycoverterapp.data.local.entity.CurrencyConversionEntity
import com.google.gson.annotations.SerializedName

data class CurrencyConversionDTO(
    @SerializedName("base_code")
    val baseCode: String,
    @SerializedName("conversion_rates")
    val conversionRates: Map<String, Double>,
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

fun CurrencyConversionDTO.toCurrencyConversionDTO(): CurrencyConversionEntity {
    return CurrencyConversionEntity(
        baseCode = baseCode,
        conversionRates = conversionRates,
        documentation = documentation,
        result = result,
        termsOfUse = termsOfUse,
        timeLastUpdateUnix = timeLastUpdateUnix,
        timeLastUpdateUtc = timeLastUpdateUtc,
        timeNextUpdateUtc = timeNextUpdateUtc,
        timeNextUpdateUnix = timeNextUpdateUnix
    )
}