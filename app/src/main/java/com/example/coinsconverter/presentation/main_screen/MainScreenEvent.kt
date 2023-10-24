package com.example.coinsconverter.presentation.main_screen

sealed class MainScreenEvent {
    object FromCurrencySelect : MainScreenEvent()
    object ToCurrencySelect : MainScreenEvent()
    object SwapIconClicked : MainScreenEvent()
    data class BottomSheetItemClicked(val value: String) : MainScreenEvent()
    data class CalculatorButtonItemClicked(val value: String) : MainScreenEvent()
}
