package com.szabolcs.revoluttest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.szabolcs.revoluttest.data.model.CurrencyDataState
import com.szabolcs.revoluttest.data.model.CurrenciesResponse

class CurrenciesRepositoryImpl : CurrenciesRepository {

    override fun getCurrencies(base: String): LiveData<CurrencyDataState<CurrenciesResponse>> {
        val response = MutableLiveData<CurrencyDataState<CurrenciesResponse>>()

        return response
    }
}