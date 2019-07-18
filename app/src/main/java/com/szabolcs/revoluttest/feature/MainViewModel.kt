package com.szabolcs.revoluttest.feature

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.szabolcs.revoluttest.data.interactor.CurrenciesInteractor

class MainViewModel(private val currenciesInteractor: CurrenciesInteractor) : ViewModel() {

    fun getCurrencies(lifecycleOwner: LifecycleOwner) {
        currenciesInteractor.getCurrencies("EUR").observe(lifecycleOwner, Observer {
            // TODO Update the adapter, handle loading, handle error
        })
    }
}