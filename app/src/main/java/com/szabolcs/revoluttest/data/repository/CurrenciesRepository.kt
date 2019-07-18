package com.szabolcs.revoluttest.data.repository

import androidx.lifecycle.LiveData
import com.szabolcs.revoluttest.data.model.CurrencyDataState
import com.szabolcs.revoluttest.data.model.CurrenciesResponse

interface CurrenciesRepository {
    fun getCurrencies(base: String): LiveData<CurrencyDataState<CurrenciesResponse>>
}