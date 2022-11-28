package com.example.currencycoverterapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.currencycoverterapp.data.local.entity.CurrencyConversionEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CurrencyConversionDatabaseTest {

    private lateinit var db: CurrencyConversionDatabase
    private lateinit var dao: CurrencyConversionDao
    val map: HashMap<String, Double> = hashMapOf()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CurrencyConversionDatabase::class.java
        ).addTypeConverter(Converters()).allowMainThreadQueries().build()

        dao = db.dao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertCurrenciesDataToDB() = runBlocking {
        map["AED"] = 3.67
        map["USD"] = 1.0
        val currencyConversionData = CurrencyConversionEntity(
            "USD", map, "doc",
            "success", "terms",
            345,
            "timeLastUpdateUnix",
            440, "timeNextUpdateUnix", 1
        )

        dao.insertCurrencies(currencyConversionData)

        val getCurrencies = dao.getCurrencies()

        assertThat(getCurrencies).isEqualTo(currencyConversionData)
    }

}