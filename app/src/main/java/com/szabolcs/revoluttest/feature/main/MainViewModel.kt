package com.szabolcs.revoluttest.feature.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.szabolcs.revoluttest.data.interactor.CurrenciesInteractor

class MainViewModel(private val currenciesInteractor: CurrenciesInteractor) : ViewModel() {

    val snackbarMessage = MutableLiveData<String>()
    val currencies = MutableLiveData<List<CurrencyViewModel>>()

    fun getCurrencies(lifecycleOwner: LifecycleOwner) {
        currenciesInteractor.getCurrencies("EUR").observe(lifecycleOwner, Observer { value ->
            value.error?.let { error ->
                snackbarMessage.value = error.message
                return@Observer
            }
            value.currencies?.let {
                currencies.value = it
            }
        })
    }
}