package com.example.currencycoverterapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencycoverterapp.domain.model.CurrencyConversion

@Entity(tableName = "currencies_conversion")
data class CurrencyConversionEntity(
    val baseCode: String,
    val conversionRates: Map<String, Double>,
    val documentation: String,
    val result: String,
    val termsOfUse: String,
    val timeLastUpdateUnix: Int,
    val timeLastUpdateUtc: String,
    val timeNextUpdateUnix: Int,
    val timeNextUpdateUtc: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)

fun CurrencyConversionEntity.toCurrencyConversion(): CurrencyConversion {
    return CurrencyConversion(
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