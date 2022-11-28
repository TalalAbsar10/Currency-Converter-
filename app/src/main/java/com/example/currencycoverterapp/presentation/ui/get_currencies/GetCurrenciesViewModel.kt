package com.example.currencycoverterapp.presentation.ui.get_currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencycoverterapp.common.Resource
import com.example.currencycoverterapp.domain.use_cases.GetCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GetCurrenciesViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GetCurrenciesState())
    val state: StateFlow<GetCurrenciesState> = _state

    fun getCurrencies(currency: String) {
        getCurrenciesUseCase(currency).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = GetCurrenciesState(getCurrencies = result.data)
                }
                is Resource.Loading -> {
                    _state.value = GetCurrenciesState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = GetCurrenciesState(
                        getCurrencies = result.data, error = result.error
                        //error = result.message ?: Constants.ERROR_MESSAGE_HTTP_EXCEPTION
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}