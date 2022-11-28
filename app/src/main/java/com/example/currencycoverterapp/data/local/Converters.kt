package com.example.currencycoverterapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
) {
    @TypeConverter
    fun fromMapToConversionRates(value: String): Map<String, Double> =
        Gson().fromJson(value, object : TypeToken<Map<String, Double>>() {}.type)

    @TypeConverter
    fun fromConversionRatesToMap(value: Map<String, Double>): String =
        Gson().toJson(value)
}