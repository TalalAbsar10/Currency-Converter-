package com.example.currencycoverterapp.data.remote

import com.example.currencyconverterapp.data.remote.dto.CurrencyConverisonDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyConversionAPI {

    @GET("{base-code}")
    suspend fun getCurrencies(
        @Path("base-code") baseCode: String
    ): CurrencyConverisonDTO
}