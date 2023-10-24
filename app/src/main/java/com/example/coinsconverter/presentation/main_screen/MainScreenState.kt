package com.example.coinsconverter.presentation.main_screen

import com.example.coinsconverter.domain.model.CurrencyRate

data class MainScreenState(
    val fromCurrencyCode: String = "INR",
    val toCurrencyCode: String = "USD",
    val fromCurrencyValue: String = "0.00",
    val toCurrencyValue: String = "0.00",
    val currencyRate: Map<String, CurrencyRate> = emptyMap(),
    val error: String? = null,
    val selection: SelectionState = SelectionState.FROM
)


enum class SelectionState {
    FROM,
    TO
}
