package com.example.currencycoverterapp.presentation.get_currencies

import com.example.currencycoverterapp.domain.model.CurrencyConversion

data class GetCurrenciesState(
    val isLoading: Boolean = false,
    val getCurrencies: CurrencyConversion? = null,
    val error: String = ""
)
