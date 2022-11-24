package com.example.currencycoverterapp.data.remote

import com.example.currencycoverterapp.data.remote.dto.CurrencyConversionDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyConversionAPI {

    @GET("{base-code}")
    suspend fun getCurrencies(
        @Path("base-code") baseCode: String
    ): CurrencyConversionDTO
}