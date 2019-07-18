package com.szabolcs.revoluttest.data.interactor

import androidx.lifecycle.LiveData
import com.szabolcs.revoluttest.data.model.CurrenciesResponse
import com.szabolcs.revoluttest.data.model.CurrencyDataState
import com.szabolcs.revoluttest.feature.CurrencyViewModel

interface CurrenciesUseCase {

    fun getCurrencies(base: String): LiveData<CurrencyDataState<CurrencyViewModel>>
}