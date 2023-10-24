package com.example.coinsconverter.di

import android.app.Application
import androidx.room.Room
import com.example.coinsconverter.data.local.CurrencyRateDatabase
import com.example.coinsconverter.data.remote.CurrencyApi
import com.example.coinsconverter.data.remote.CurrencyApi.Companion.BASE_URL
import com.example.coinsconverter.data.repository.CurrencyRepositoryImp
import com.example.coinsconverter.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // In this Class ->
    // It sets up the necessary dependencies. and ensures that a single instance of CurrencyApi and CurrencyRateDatabase are used throughout the application.

    // This method provides an instance of CurrencyApi
    @Provides
    @Singleton
    fun provideStockApi(): CurrencyApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CurrencyApi::class.java)
    }


    // This method provides an instance of CurrencyRateDatabase
    @Provides
    @Singleton
    fun provideCurrencyRateDatabase(application: Application): CurrencyRateDatabase {
        return Room.databaseBuilder(
            application,
            CurrencyRateDatabase::class.java,
            "currency-db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: CurrencyApi,
        db: CurrencyRateDatabase
    ): CurrencyRepository {
        return CurrencyRepositoryImp(
            api = api,
            db = db
        )
    }

}