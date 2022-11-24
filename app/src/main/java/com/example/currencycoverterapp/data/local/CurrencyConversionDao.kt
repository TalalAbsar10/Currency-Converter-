package com.example.currencycoverterapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencycoverterapp.data.local.entity.CurrencyConversionEntity

@Dao
interface CurrencyConversionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: CurrencyConversionEntity)

    @Query("SELECT * FROM currencies_conversion")
    suspend fun getCurrencies(): CurrencyConversionEntity
}