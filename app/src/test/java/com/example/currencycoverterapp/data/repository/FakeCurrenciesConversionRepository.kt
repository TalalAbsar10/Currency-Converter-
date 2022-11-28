package com.example.currencycoverterapp.data.repository

import com.example.currencycoverterapp.common.Resource
import com.example.currencycoverterapp.domain.model.CurrencyConversion
import com.example.currencycoverterapp.domain.repository.CurrencyConversionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCurrenciesConversionRepository : CurrencyConversionRepository {

    private lateinit var currencyConversion: CurrencyConversion

    override fun getCurrencies(currency: String): Flow<Resource<CurrencyConversion>> {
        return flow { emit(Resource.Success(data = currencyConversion)) }
    }

}