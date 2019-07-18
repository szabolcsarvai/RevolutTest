package com.szabolcs.revoluttest.data.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.szabolcs.revoluttest.data.model.CurrenciesResultState
import com.szabolcs.revoluttest.data.repository.CurrenciesRepositoryImpl
import com.szabolcs.revoluttest.utils.transformResponse

class CurrenciesInteractor(private val repository: CurrenciesRepositoryImpl) : CurrenciesUseCase {

    override fun getCurrencies(base: String): LiveData<CurrenciesResultState> {
        return MediatorLiveData<CurrenciesResultState>().apply {
            addSource(repository.getCurrencies(base)) { value = transformResponse(it) }
        }
    }
}