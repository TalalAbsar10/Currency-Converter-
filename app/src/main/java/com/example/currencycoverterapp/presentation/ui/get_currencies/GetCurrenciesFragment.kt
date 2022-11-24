package com.example.currencyconverterapp.presentation.get_currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.example.currencycoverterapp.R
import com.example.currencycoverterapp.databinding.FragmentGetCurrenciesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetCurrenciesFragment : Fragment() {

    val viewModel: GetCurrenciesViewModel by viewModels()
    private lateinit var binding: FragmentGetCurrenciesBinding

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

        viewModel.getCurrencies("PHP")
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
                    val listFromCurrency = ArrayList(it.conversionRates.keys)
                    val arrayAdapterFromCurrency =
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            listFromCurrency
                        )
                    binding.spFromCurrency.adapter = arrayAdapterFromCurrency

                    val listToCurrency = ArrayList(it.conversionRates.keys)
                    val arrayAdapterToCurrency =
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            listToCurrency
                        )
                    binding.spFromCurrency.adapter = arrayAdapterToCurrency

                    Toast.makeText(
                        requireContext(),
                        binding.spFromCurrency.selectedItem.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.tvAmount.text = it.conversionRates.getValue("USD").toString()
                }
            }
        }
        binding.btnCalculate.setOnClickListener {
        }
    }
}