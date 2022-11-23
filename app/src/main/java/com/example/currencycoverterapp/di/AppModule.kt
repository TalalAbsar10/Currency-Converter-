package com.example.currencycoverterapp.di

import com.example.currencyconverterapp.common.Constants
import com.example.currencycoverterapp.data.remote.CurrencyConversionAPI
import com.example.currencycoverterapp.data.repository.CurrencyConversionRepositoryImpl
import com.example.currencycoverterapp.domain.repository.CurrencyConversionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCurrencyConversionAPI(): CurrencyConversionAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyConversionAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyConversionRepository(api: CurrencyConversionAPI): CurrencyConversionRepository {
        return CurrencyConversionRepositoryImpl(api)

    }
}