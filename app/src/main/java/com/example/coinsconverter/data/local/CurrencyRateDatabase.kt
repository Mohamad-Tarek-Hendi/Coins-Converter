package com.example.coinsconverter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CurrencyRateEntity::class],
    version = 1
)
abstract class CurrencyRateDatabase : RoomDatabase() {
    abstract val dao: CurrencyRateDao
}