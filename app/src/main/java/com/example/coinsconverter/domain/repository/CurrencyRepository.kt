package com.example.coinsconverter.domain.repository

import com.example.coinsconverter.domain.model.CurrencyRate
import com.example.coinsconverter.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun getAllCurrencyRates(): Flow<Resource<List<CurrencyRate>>>
}