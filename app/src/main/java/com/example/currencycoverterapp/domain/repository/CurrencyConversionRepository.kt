package com.example.currencycoverterapp.domain.repository

import com.example.currencyconverterapp.data.remote.dto.CurrencyConverisonDTO

interface CurrencyConversionRepository {

    suspend fun getCurrencies(currency: String): CurrencyConverisonDTO
}