package com.example.coinsconverter.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyRateEntity(
    @PrimaryKey
    val id: Int? = null,
    val code: String,
    val name: String,
    val rate: Double
)
