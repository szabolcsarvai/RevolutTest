package com.szabolcs.revoluttest.feature.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.szabolcs.revoluttest.data.interactor.CurrenciesInteractor
import com.szabolcs.revoluttest.feature.main.adapter.CurrencyViewModel
import com.szabolcs.revoluttest.feature.main.adapter.VM2

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
                val list = mutableListOf<CurrencyViewModel>()
                value.selectedCurrency?.let {
                    val selectedViewModel = VM2(value.selectedCurrency)
                    list.add(selectedViewModel)

                }
                list.addAll(it)
                currencies.value = list
            }
        })
    }
}