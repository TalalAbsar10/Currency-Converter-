package com.example.currencycoverterapp.presentation.ui.get_currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.currencycoverterapp.R
import com.example.currencycoverterapp.databinding.FragmentGetCurrenciesBinding
import com.example.currencycoverterapp.domain.model.CurrencyConversion
import com.example.currencycoverterapp.presentation.get_currencies.GetCurrenciesViewModel
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
        callAPI("USD") //Call it first to populate the spinners
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.state.collect {
                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }

                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                }

                it.getCurrencies?.let {
                    binding.progressBar.visibility = View.GONE
                    populateToCurrency(ArrayList(it.conversionRates.keys))
                    populateFromCurrency(ArrayList(it.conversionRates.keys))
                    data = it
                }
            }
        }

        binding.btnCalculate.setOnClickListener {
            selectedCurrencyConversionRate = formatToToDigits(
                data.conversionRates.getValue(binding.spToCurrency.selectedItem.toString())
            ).toString()
            if (binding.etAmount.text.toString() != "") {
                val calculatedAmount =
                    calculate(
                        Integer.parseInt(binding.etAmount.text.toString()),
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
            }
        }
    }

    private fun calculate(amount: Int, toCurrency: Double): Double {
        val calculatedAmount = amount.toDouble() * toCurrency
        return formatToToDigits(calculatedAmount)
    }

    private fun formatToToDigits(number: Double): Double {
        return String.format("%.2f", number).toDouble()
    }

    private fun populateToCurrency(list: List<String>) {
        val arrayAdapterToCurrency =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                list
            )
        binding.spToCurrency.adapter = arrayAdapterToCurrency
    }

    private fun populateFromCurrency(list: List<String>) {
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
    }
}