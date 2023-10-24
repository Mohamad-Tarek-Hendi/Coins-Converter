package com.example.coinsconverter.data.remote

import com.example.coinsconverter.data.remote.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("v1/latest")
    suspend fun getLatestRates(
        @Query("apikey") apiKey: String = API_KEY
    ): CurrencyDto

    companion object {
        val API_KEY = "fca_live_FctahP7gx1p6kLFUL9d3uUQeuvWvc5Q2LeeWeSIr"
        val BASE_URL = "https://api.freecurrencyapi.com/"
    }
}