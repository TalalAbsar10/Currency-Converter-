package com.example.currencycoverterapp.di

import android.app.Application
import androidx.room.Room
import com.example.currencyconverterapp.common.Constants
import com.example.currencycoverterapp.data.local.Converters
import com.example.currencycoverterapp.data.local.CurrencyConversionDatabase
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
    fun provideCurrencyConversionRepository(
        api: CurrencyConversionAPI,
        db: CurrencyConversionDatabase
    ): CurrencyConversionRepository {
        return CurrencyConversionRepositoryImpl(api, db.dao)

    }

    @Provides
    @Singleton
    fun provideCurrencyConversionDatabase(app: Application): CurrencyConversionDatabase {
        return Room.databaseBuilder(
            app, CurrencyConversionDatabase::class.java, "currencies_conversion_db"
        ).addTypeConverter(Converters())
            .fallbackToDestructiveMigration()
            .build()
    }
}