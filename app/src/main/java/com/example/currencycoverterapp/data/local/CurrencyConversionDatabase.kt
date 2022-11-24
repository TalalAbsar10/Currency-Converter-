package com.example.currencycoverterapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.currencycoverterapp.data.local.entity.CurrencyConversionEntity

@Database(entities = [CurrencyConversionEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class CurrencyConversionDatabase : RoomDatabase() {

    abstract val dao: CurrencyConversionDao

}