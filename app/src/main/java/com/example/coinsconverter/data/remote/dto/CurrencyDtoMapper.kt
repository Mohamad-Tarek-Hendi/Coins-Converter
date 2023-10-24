package com.example.coinsconverter.data.remote.dto

import com.example.coinsconverter.domain.model.CurrencyRate

fun CurrencyDto.toCurrencyRate(): List<CurrencyRate> {
    val currentData = this.data

    return listOf(
        CurrencyRate("INR", "Indian Rupee", currentData.INR),
        CurrencyRate("EUR", "Euro", currentData.EUR),
        CurrencyRate("USD", "US Dollar", currentData.USD),
        CurrencyRate("JPY", "Japanese Yen", currentData.JPY),
        CurrencyRate("BGN", "Bulgarian Lev", currentData.BGN),
        CurrencyRate("CZK", "Czech Republic Koruna", currentData.CZK),
        CurrencyRate("DKK", "Danish Krone", currentData.DKK),
        CurrencyRate("GBP", "British Pound Sterling", currentData.GBP),
        CurrencyRate("HUF", "Hungarian Forint", currentData.HUF),
        CurrencyRate("PLN", "Polish Zloty", currentData.PLN),
        CurrencyRate("RON", "Romanian Leu", currentData.RON),
        CurrencyRate("SEK", "Swedish Krona", currentData.SEK),
        CurrencyRate("CHF", "Swiss Franc", currentData.CHF),
        CurrencyRate("ISK", "Icelandic Króna", currentData.ISK),
        CurrencyRate("NOK", "Norwegian Krone", currentData.NOK),
        CurrencyRate("HRK", "Croatian Kuna", currentData.HRK),
        CurrencyRate("RUB", "Russian Ruble", currentData.RUB),
        CurrencyRate("TRY", "Turkish Lira", currentData.TRY),
        CurrencyRate("AUD", "Australian Dollar", currentData.AUD),
        CurrencyRate("BRL", "Brazilian Real", currentData.BRL),
        CurrencyRate("CAD", "Canadian Dollar", currentData.CAD),
        CurrencyRate("CNY", "Chinese Yuan", currentData.CNY),
        CurrencyRate("HKD", "Hong Kong Dollar", currentData.HKD),
        CurrencyRate("IDR", "Indonesian Rupiah", currentData.IDR),
        CurrencyRate("ILS", "Israeli New Sheqel", currentData.ILS),
        CurrencyRate("KRW", "South Korean Won", currentData.KRW),
        CurrencyRate("MXN", "Mexican Peso", currentData.MXN),
        CurrencyRate("MYR", "Malaysian Ringgit", currentData.MYR),
        CurrencyRate("NZD", "New Zealand Dollar", currentData.NZD),
        CurrencyRate("PHP", "Philippine Peso", currentData.PHP),
        CurrencyRate("SGD", "Singapore Dollar", currentData.SGD),
        CurrencyRate("THB", "Thai Baht", currentData.THB),
        CurrencyRate("ZAR", "South African Rand", currentData.ZAR)
    )
}