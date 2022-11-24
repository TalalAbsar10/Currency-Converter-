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
class GetCurrenciesCalculationFragment : Fragment() {

    val viewModel: GetCurrenciesViewModel by viewModels()
    private lateinit var binding: FragmentGetCurrenciesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_currencies_calculation,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}