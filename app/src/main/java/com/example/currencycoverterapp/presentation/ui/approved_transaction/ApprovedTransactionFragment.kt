package com.example.currencycoverterapp.presentation.ui.approved_transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.currencycoverterapp.R
import com.example.currencycoverterapp.common.Constants.GREAT_NOW_YOU_HAVE
import com.example.currencycoverterapp.common.Constants.IN_YOUR_ACCOUNT
import com.example.currencycoverterapp.common.Constants.YOUR_CONVERSION_RATE_WAS_1
import com.example.currencycoverterapp.databinding.FragmentApprovedTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApprovedTransactionFragment : Fragment() {

    private lateinit var binding: FragmentApprovedTransactionBinding
    val args: ApprovedTransactionFragmentArgs by navArgs()
    var convertedAmount: String = ""
    var toCurrency: String = ""
    var selectedCurrencyConversionRate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_approved_transaction,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        convertedAmount = args.array[0]
        toCurrency = args.array[1]
        selectedCurrencyConversionRate = args.array[2]
        binding.tvText.text =
            "${GREAT_NOW_YOU_HAVE} ${convertedAmount} ${toCurrency} ${IN_YOUR_ACCOUNT}\n" +
                    "\n" + "${YOUR_CONVERSION_RATE_WAS_1}${selectedCurrencyConversionRate}"
    }

}