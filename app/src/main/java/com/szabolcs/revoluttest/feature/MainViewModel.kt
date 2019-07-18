package com.szabolcs.revoluttest.feature

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.szabolcs.revoluttest.data.interactor.CurrenciesInteractor

class MainViewModel(private val currenciesInteractor: CurrenciesInteractor) : ViewModel() {

    val snackbarMessage = MutableLiveData<String>()

    fun getCurrencies(lifecycleOwner: LifecycleOwner) {
        currenciesInteractor.getCurrencies("EUR").observe(lifecycleOwner, Observer {
            it.error?.let { error ->
                snackbarMessage.value = error.message
            }
        })
    }
}