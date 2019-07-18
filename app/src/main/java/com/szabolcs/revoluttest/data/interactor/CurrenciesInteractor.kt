package com.szabolcs.revoluttest.data.interactor

import androidx.lifecycle.LiveData
import com.szabolcs.revoluttest.data.model.CurrencyDataState
import com.szabolcs.revoluttest.data.repository.CurrenciesRepositoryImpl
import com.szabolcs.revoluttest.feature.CurrencyViewModel

class CurrenciesInteractor(private val repository: CurrenciesRepositoryImpl) : CurrenciesUseCase {

    override fun getCurrencies(base: String): LiveData<CurrencyDataState<CurrencyViewModel>> {
        repository.getCurrencies(base)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}