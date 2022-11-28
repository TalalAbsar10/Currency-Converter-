package com.example.currencycoverterapp.data.repository

import com.example.currencycoverterapp.common.Resource
import com.example.currencycoverterapp.common.networkBoundResource
import com.example.currencycoverterapp.data.local.CurrencyConversionDao
import com.example.currencycoverterapp.data.remote.CurrencyConversionAPI
import com.example.currencycoverterapp.data.remote.dto.toCurrencyConversionDTO
import com.example.currencycoverterapp.domain.model.CurrencyConversion
import com.example.currencycoverterapp.domain.repository.CurrencyConversionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyConversionRepositoryImpl @Inject constructor(
    private val api: CurrencyConversionAPI,
    private val dao: CurrencyConversionDao
) : CurrencyConversionRepository {

    override fun getCurrencies(currency: String): Flow<Resource<CurrencyConversion>> =
        networkBoundResource(
            query = {
                dao.getCurrencies()
            },
            fetch = {
                val remoteCurrencies = api.getCurrencies(currency).toCurrencyConversionDTO()
                remoteCurrencies
            },
            saveFetchResult = {
                dao.insertCurrencies(it)
            }
        )
}
