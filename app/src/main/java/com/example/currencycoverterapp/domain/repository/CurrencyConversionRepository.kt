package com.example.currencycoverterapp.domain.repository

import com.example.currencyconverterapp.common.Resource
import com.example.currencycoverterapp.domain.model.CurrencyConversion
import kotlinx.coroutines.flow.Flow

interface CurrencyConversionRepository {

    fun getCurrencies(currency: String): Flow<Resource<CurrencyConversion>>
}