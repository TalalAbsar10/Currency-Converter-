package com.example.currencycoverterapp.domain.use_cases

import com.example.currencycoverterapp.common.Resource
import com.example.currencycoverterapp.data.repository.FakeCurrenciesConversionRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCurrenciesUseCaseTest {

    private lateinit var getCurrenciesUseCase: GetCurrenciesUseCase
    private lateinit var fakeRepository: FakeCurrenciesConversionRepository

    @Before
    fun setup() {
        fakeRepository = FakeCurrenciesConversionRepository()
        getCurrenciesUseCase = GetCurrenciesUseCase(fakeRepository)
    }

    @Test
    fun get_Currencies(): Unit = runBlocking {
        val flow = getCurrenciesUseCase.invoke("USD")
        flow.onEach { result ->
            when (result) {
                is Resource.Success -> {
                    assertThat((result.data?.result.equals("success"))).isTrue()
                }
                else -> {}
            }
        }
    }
}