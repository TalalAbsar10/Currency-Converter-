package com.example.currencycoverterapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "currencies_conversion", indices = [androidx.room.Index(
        value = ["result"],
        unique = true
    )]
)
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