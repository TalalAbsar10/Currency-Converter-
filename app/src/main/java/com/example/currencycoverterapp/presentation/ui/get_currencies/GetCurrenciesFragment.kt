package com.example.currencycoverterapp.presentation.ui.get_currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.currencycoverterapp.R
import com.example.currencycoverterapp.common.Constants
import com.example.currencycoverterapp.common.Constants.BASE_CURRENCY
import com.example.currencycoverterapp.databinding.FragmentGetCurrenciesBinding
import com.example.currencycoverterapp.domain.model.CurrencyConversion
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GetCurrenciesFragment : Fragment() {

    val viewModel: GetCurrenciesViewModel by viewModels()
    private lateinit var binding: FragmentGetCurrenciesBinding
    lateinit var data: CurrencyConversion
    var selectedCurrencyConversionRate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_get_currencies,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCallService.setOnClickListener {
            callAPI(BASE_CURRENCY)
        }
        binding.btnCalculate.setOnClickListener {
            if (binding.etAmount.text.toString() != "") {
                selectedCurrencyConversionRate = formatToToDigits(
                    data.conversionRates.getValue(binding.spToCurrency.selectedItem.toString())
                ).toString()
                val calculatedAmount =
                    calculate(
                        Integer.parseInt(binding.etAmount.text.toString()),
                        data.conversionRates.getValue(binding.spFromCurrency.selectedItem.toString()),
                        data.conversionRates.getValue(binding.spToCurrency.selectedItem.toString())
                    ).toString()

                val array = arrayOf(
                    binding.etAmount.text.toString(),
                    calculatedAmount,
                    binding.spFromCurrency.selectedItem.toString(),
                    binding.spToCurrency.selectedItem.toString(),
                    selectedCurrencyConversionRate
                )
                findNavController().navigate(
                    GetCurrenciesFragmentDirections.actionGetCurrenciesFragmentToGetCurrenciesCalculationFragment(
                        array
                    )
                )
            } else Toast.makeText(
                requireContext(),
                Constants.AMOUNT_CAN_NOT_BE_EMPTY,
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private fun calculate(enteredAmount: Int, fromCurrency: Double, toCurrency: Double): Double {
        val baseCurrencyUSD = 1
        var finalConvertedAmount = 0.0
        var amountConvertedToBaseCurrency = 0.0
        var amountConvertedToToCurrency = 0.0

        amountConvertedToBaseCurrency = baseCurrencyUSD / fromCurrency
        amountConvertedToToCurrency = amountConvertedToBaseCurrency * toCurrency
        finalConvertedAmount = amountConvertedToToCurrency * enteredAmount

        val calculatedAmount = finalConvertedAmount
        selectedCurrencyConversionRate = formatToToDigits(amountConvertedToToCurrency).toString()
        return formatToToDigits(calculatedAmount)
    }

    private fun formatToToDigits(number: Double): Double {
        return String.format("%.2f", number).toDouble()
    }

    private fun populateToCurrency(list: List<Any>) {
        val arrayAdapterToCurrency =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                list
            )
        binding.spToCurrency.adapter = arrayAdapterToCurrency
    }

    private fun populateFromCurrency(list: List<Any>) {
        val arrayAdapterFromCurrency =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                list
            )
        binding.spFromCurrency.adapter = arrayAdapterFromCurrency
    }

    private fun callAPI(currency: String) {
        viewModel.getCurrencies(currency)
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.state.collect {
                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }

                if (it.error?.message?.isNotBlank() == true) {
                    binding.progressBar.visibility = View.GONE
                }

                it.getCurrencies?.let {
                    binding.progressBar.visibility = View.GONE
                    val list = ArrayList<String>()
                    list.add(Constants.SELECT)
                    for (s in it.conversionRates.keys) {
                        list.add(s)
                    }
                    populateToCurrency(ArrayList(list))
                    populateFromCurrency(ArrayList(list))
                    data = it
                }
            }
        }
    }
}