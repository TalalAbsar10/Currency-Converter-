package com.example.currencycoverterapp.common

object Constants {

    const val BASE_URL = "https://v6.exchangerate-api.com/v6/808bcff017088de894212c14/latest/"

    const val ERROR_MESSAGE_HTTP_EXCEPTION = "An unexpected occurred"
    const val ERROR_MESSAGE_IO_EXCEPTION =
        "Couldn't connect to the internet, please check your connection"

    //For Get Currencies Fragment
    const val BASE_CURRENCY = "USD"
    const val SELECT = "Select"
    const val AMOUNT_CAN_NOT_BE_EMPTY = "Amount can not be empty"

    //For Currencies Calculation Fragment
    const val GREAT_NOW_YOU_HAVE = "Great now you have"
    const val IN_YOUR_ACCOUNT = "in your account."
    const val APPROVAL_REQUIRED = "Approval Required"
    const val APPROVE = "Approve"
    const val CANCEL = "Cancel"

    //For Approved Transaction Fragment
    const val YOUR_CONVERSION_RATE_WAS_1 = "Your conversion rate was 1/"
    const val YOU_ARE_ABOUT_TO_GET = "You are about to get"
    const val DO_YOU_APPROVE_THIS_TRANSACTION = "Do you approve this transaction?"
    const val FOR = "for"

}