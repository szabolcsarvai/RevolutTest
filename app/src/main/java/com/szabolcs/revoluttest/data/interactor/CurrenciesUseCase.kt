package com.szabolcs.revoluttest.data.interactor

import androidx.lifecycle.LiveData
import com.szabolcs.revoluttest.data.model.CurrenciesResultState

interface CurrenciesUseCase {

    fun getCurrencies(base: String): LiveData<CurrenciesResultState>

}