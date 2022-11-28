package com.example.currencycoverterapp.presentation.ui.get_currencies_calculation

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.currencycoverterapp.R
import com.example.currencycoverterapp.common.Constants.APPROVAL_REQUIRED
import com.example.currencycoverterapp.common.Constants.APPROVE
import com.example.currencycoverterapp.common.Constants.CANCEL
import com.example.currencycoverterapp.common.Constants.DO_YOU_APPROVE_THIS_TRANSACTION
import com.example.currencycoverterapp.common.Constants.FOR
import com.example.currencycoverterapp.common.Constants.YOU_ARE_ABOUT_TO_GET
import com.example.currencycoverterapp.databinding.FragmentCurrenciesCalculationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetCurrenciesCalculationFragment : Fragment() {

    private lateinit var binding: FragmentCurrenciesCalculationBinding
    val args: GetCurrenciesCalculationFragmentArgs by navArgs()
    private lateinit var timer: CountDownTimer
    var currentMillis: Long = 0
    var enteredAmount: String = ""
    var convertedAmount: String = ""
    var fromCurrency: String = ""
    var toCurrency: String = ""
    var selectedCurrencyConversionRate: String = ""

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
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enteredAmount = args.array[0]
        convertedAmount = args.array[1]
        fromCurrency = args.array[2]
        toCurrency = args.array[3]
        selectedCurrencyConversionRate = args.array[4]

        binding.tvEnteredAmount.text = "$enteredAmount $fromCurrency"
        binding.tvConvertedAmount.text = "$convertedAmount $toCurrency"

        timer = object : CountDownTimer(30000, 300) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.setText("" + millisUntilFinished / 1000)
                currentMillis = millisUntilFinished
            }

            override fun onFinish() {
                findNavController().navigate(
                    GetCurrenciesCalculationFragmentDirections.actionGetCurrenciesCalculationFragmentToGetCurrenciesFragment()
                )
            }
        }.start()

        binding.btnConvert.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(activity!!)
            dialogBuilder.setMessage(
                "${YOU_ARE_ABOUT_TO_GET} ${convertedAmount} ${toCurrency} " +
                        "${FOR} ${enteredAmount} ${fromCurrency}.\n" +
                        "${DO_YOU_APPROVE_THIS_TRANSACTION}"
            )
                .setCancelable(false)
                .setPositiveButton("${APPROVE}") { dialog, id ->
                    dialog.dismiss()
                    val array = arrayOf(convertedAmount, toCurrency, selectedCurrencyConversionRate)
                    findNavController().navigate(
                        GetCurrenciesCalculationFragmentDirections.actionGetCurrenciesCalculationFragmentToApprovedTransactionFragment(
                            array
                        )
                    )

                }.setNegativeButton("${CANCEL}") { dialog, id ->
                    dialog.dismiss()
                    timer = object : CountDownTimer(currentMillis, 300) {
                        override fun onTick(millisUntilFinished: Long) {
                            currentMillis = millisUntilFinished
                            binding.tvTimer.text = (millisUntilFinished / 1000).toString() + ""
                        }

                        override fun onFinish() {}
                    }.start()
                }

            val alert = dialogBuilder.create()
            alert.setTitle("${APPROVAL_REQUIRED}")
            alert.show()
            timer.cancel()
        }
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    override fun onDestroy() {
        super.onPause()
    }

}