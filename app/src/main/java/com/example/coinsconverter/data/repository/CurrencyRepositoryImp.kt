package com.example.coinsconverter.data.repository

import com.example.coinsconverter.data.local.CurrencyRateDatabase
import com.example.coinsconverter.data.local.toCurrencyRate
import com.example.coinsconverter.data.local.toCurrencyRateEntity
import com.example.coinsconverter.data.remote.CurrencyApi
import com.example.coinsconverter.data.remote.dto.toCurrencyRate
import com.example.coinsconverter.domain.model.CurrencyRate
import com.example.coinsconverter.domain.repository.CurrencyRepository
import com.example.coinsconverter.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CurrencyRepositoryImp(
    private val api: CurrencyApi,
    private val db: CurrencyRateDatabase
) : CurrencyRepository {

    private val dao = db.dao

    override suspend fun getAllCurrencyRates(): Flow<Resource<List<CurrencyRate>>> {
        return flow {
            val getLocalCurrencyRates = getLocalCurrencyRates()
            emit(Resource.Success(getLocalCurrencyRates))

            try {
                val ratesFromApi = getRemoteCurrencyRates()

                // delete database
                dao.clearCurrencyRateListing()

                //Update database
                updateLocalCurrencyRate(ratesFromApi)

                emit(Resource.Success(ratesFromApi))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't Load Data.. ${e.message}"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't Load Data..${e.message}"))
            }
        }
    }

    // From local
    private suspend fun getLocalCurrencyRates(): List<CurrencyRate> {
        return dao.getAllCurrencyRates().map {
            it.toCurrencyRate()
        }
    }

    // From remote
    private suspend fun getRemoteCurrencyRates(): List<CurrencyRate> {
        val response = api.getLatestRates()
        return response.toCurrencyRate()
    }

    // Update local database
    private suspend fun updateLocalCurrencyRate(currencyRate: List<CurrencyRate>) {
        dao.upsert(currencyRate.map {
            it.toCurrencyRateEntity()
        })
    }
}