package com.example.currencycoverterapp.data.repository


import com.example.currencyconverterapp.data.remote.dto.CurrencyConverisonDTO
import com.example.currencycoverterapp.data.remote.CurrencyConversionAPI
import com.example.currencycoverterapp.domain.repository.CurrencyConversionRepository
import javax.inject.Inject

class CurrencyConversionRepositoryImpl @Inject constructor(
    private val api: CurrencyConversionAPI
) : CurrencyConversionRepository {
    override suspend fun getCurrencies(currency: String): CurrencyConverisonDTO {
        return api.getCurrencies(currency)
    }
}