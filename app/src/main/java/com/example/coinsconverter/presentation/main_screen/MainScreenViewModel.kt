package com.example.coinsconverter.presentation.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinsconverter.domain.repository.CurrencyRepository
import com.example.coinsconverter.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: CurrencyRepository) :
    ViewModel() {

    var state by mutableStateOf(MainScreenState())

    init {
        getCurrencyRateList()
    }

    // Function for handling an event
    fun onEvent(event: MainScreenEvent) {
        when (event) {

            MainScreenEvent.FromCurrencySelect -> {
                // Update state
                state = state.copy(
                    selection = SelectionState.FROM
                )
            }

            MainScreenEvent.ToCurrencySelect -> {
                state = state.copy(
                    selection = SelectionState.TO
                )
            }

            MainScreenEvent.SwapIconClicked -> {
                state = state.copy(
                    fromCurrencyCode = state.toCurrencyCode,
                    fromCurrencyValue = state.toCurrencyValue,
                    toCurrencyCode = state.fromCurrencyCode,
                    toCurrencyValue = state.fromCurrencyValue
                )
            }

            is MainScreenEvent.BottomSheetItemClicked -> TODO()

            is MainScreenEvent.CalculatorButtonItemClicked -> {
                updateCurrencyValue(event.value)
            }


        }
    }

    // Get currency rate list from repository
    private fun getCurrencyRateList() {
        viewModelScope.launch {
            repository.getAllCurrencyRates().collectLatest { each_result ->
                when (each_result) {

                    is Resource.Success -> {
                        state = state.copy(
                            // transform the list into a map,
                            currencyRate = each_result.data?.associateBy { it.code } ?: emptyMap(),
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            currencyRate = each_result.data?.associateBy { it.code } ?: emptyMap(),
                            error = each_result.message
                        )
                    }

                    is Resource.Loading -> TODO()


                }
            }
        }
    }

    // Update currency value
    private fun updateCurrencyValue(value: String) {

        // Retrieve the current currency value based on the selection state (From or To)
        val currentCurrencyValue = when (state.selection) {
            SelectionState.FROM -> state.fromCurrencyValue
            SelectionState.TO -> state.toCurrencyValue
        }

        // Retrieve the currency rates for the selected currencies
        val fromCurrencyRate = state.currencyRate[state.fromCurrencyCode]?.rate ?: 0.0
        val toCurrencyRate = state.currencyRate[state.toCurrencyCode]?.rate ?: 0.0

        // Retrieve the currency value based on user input
        val updatedCurrencyValue = when (value) {
            "C" -> "0.00"
            else -> if (currentCurrencyValue == "0.00") value else currentCurrencyValue + value
        }

        // Create a number format to format the resulting currency values with two decimal places
        val numberFormat = DecimalFormat("#.00")

        when (state.selection) {

            SelectionState.FROM -> {

                // Convert the updated currency value to a Double
                val fromValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0

                // Calculate the corresponding value for the TO currency using currency rates
                val toValue = fromValue / fromCurrencyRate * toCurrencyRate

                // Update the state with the new currency values
                state = state.copy(
                    fromCurrencyValue = updatedCurrencyValue,
                    toCurrencyValue = numberFormat.format(toValue)
                )
            }

            SelectionState.TO -> {

                // Convert the updated currency value to a Double
                val toValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0

                // Calculate the corresponding value for the FROM currency using currency rates
                val fromValue = toValue / toCurrencyRate * fromCurrencyRate

                // Update the state with the new currency values
                state = state.copy(
                    toCurrencyValue = updatedCurrencyValue,
                    fromCurrencyValue = numberFormat.format(fromValue)
                )
            }
        }
    }
}