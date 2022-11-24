package com.example.currencycoverterapp.domain.use_cases

import com.example.currencycoverterapp.common.Resource
import com.example.currencycoverterapp.domain.model.CurrencyConversion
import com.example.currencycoverterapp.domain.repository.CurrencyConversionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyConversionRepository
) {
    operator fun invoke(currency: String): Flow<Resource<CurrencyConversion>> {
        if (currency.isBlank()) {
            return flow { }
        }
        return repository.getCurrencies(currency)
    }
}