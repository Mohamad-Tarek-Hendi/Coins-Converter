package com.example.coinsconverter.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(currencyRateEntity: List<CurrencyRateEntity>)

    @Query("DELETE FROM CurrencyRateEntity")
    suspend fun clearCurrencyRateListing()

    @Query("SELECT * FROM CurrencyRateEntity")
    suspend fun getAllCurrencyRates(): List<CurrencyRateEntity>
}