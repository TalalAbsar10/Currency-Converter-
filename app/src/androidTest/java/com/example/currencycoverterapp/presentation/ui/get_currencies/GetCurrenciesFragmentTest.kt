package com.example.currencycoverterapp.presentation.ui.get_currencies

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.filters.MediumTest
import com.example.currencycoverterapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class GetCurrenciesFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testGetCurrenciesFragmentFlow() {
        val amount = 100
        onView(withId(com.example.currencycoverterapp.R.id.etAmount)).perform(typeText(amount.toString()))

        onView(withId(com.example.currencycoverterapp.R.id.spFromCurrency)).perform(click())
        onData(anything()).atPosition(1).perform(click());
        onView(withId(com.example.currencycoverterapp.R.id.spFromCurrency)).check(
            matches(
                withSpinnerText(containsString("USD"))
            )
        );

        onView(withId(com.example.currencycoverterapp.R.id.spToCurrency)).perform(click())
        onData(anything()).atPosition(1).perform(click());
        onView(withId(com.example.currencycoverterapp.R.id.spFromCurrency)).check(
            matches(
                withSpinnerText(containsString("AED"))
            )
        );

        onView(withId(com.example.currencycoverterapp.R.id.btnCalculate)).perform(click())
        onView(withId(com.example.currencycoverterapp.R.id.btnCallService)).perform(click())

    }

    @Test
    fun navigateFromGetCurrenciesFragmentToCalculationFragment() {

        val navController = mock(NavController::class.java)
        `when`(navController.popBackStack()).thenReturn(true)
        launchFragmentInHiltContainer<GetCurrenciesFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(com.example.currencycoverterapp.R.id.btnCalculate)).perform(click())
        verify(navController).navigate(
            GetCurrenciesFragmentDirections.actionGetCurrenciesFragmentToGetCurrenciesCalculationFragment(
                arrayOf(
                    "10",
                    "36.7",
                    "USD",
                    "AED",
                    "3.67"
                )
            )
        )
    }
}