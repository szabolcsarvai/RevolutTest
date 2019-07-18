package com.szabolcs.revoluttest.data.repository

import androidx.lifecycle.LiveData
import com.szabolcs.revoluttest.data.model.DataState
import com.szabolcs.revoluttest.data.model.CurrenciesResponse
import com.szabolcs.revoluttest.data.model.CurrenciesResponseState

interface CurrenciesRepository {
    fun getCurrencies(base: String): LiveData<CurrenciesResponseState>
}