package com.szabolcs.revoluttest.data.repository

import androidx.lifecycle.MutableLiveData
import com.szabolcs.revoluttest.data.model.CurrenciesResponseState
import com.szabolcs.revoluttest.data.networking.NetworkingManager
import com.szabolcs.revoluttest.data.networking.ServiceError
import com.szabolcs.revoluttest.enqueueCall

class CurrenciesRepositoryImpl(private val networkingManager: NetworkingManager) : CurrenciesRepository {

    override fun getCurrencies(base: String): MutableLiveData<CurrenciesResponseState> {
        val responseData = MutableLiveData<CurrenciesResponseState>()
        networkingManager.service.getCurrencies(base).enqueueCall(
            onSuccess = {
                responseData.value = CurrenciesResponseState(selectedCurrency = it.base, currencies = it.rates)
            },
            onFailure = {
                responseData.value = CurrenciesResponseState(error = ServiceError(it.message, it.errorCode))
            }
        )
        return responseData
    }
}