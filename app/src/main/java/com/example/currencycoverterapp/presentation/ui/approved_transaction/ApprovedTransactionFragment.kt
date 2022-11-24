package com.example.currencycoverterapp.presentation.ui.approved_transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.currencycoverterapp.R
import com.example.currencycoverterapp.databinding.FragmentApprovedTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApprovedTransactionFragment : Fragment() {

    private lateinit var binding: FragmentApprovedTransactionBinding
    val args: ApprovedTransactionFragmentArgs by navArgs()
    var convertedAmount: String = ""
    var toCurrency: String = ""
    var selectedCurrencyConversionRate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
            "Great now you have ${convertedAmount} ${toCurrency} in your account.\n" +
                    "\n" + "Your conversion rate was 1/${selectedCurrencyConversionRate}"
    }

}