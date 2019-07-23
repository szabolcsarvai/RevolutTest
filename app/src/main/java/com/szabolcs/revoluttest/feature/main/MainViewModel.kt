package com.szabolcs.revoluttest.feature.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.szabolcs.revoluttest.data.interactor.CurrenciesInteractor

class MainViewModel(private val currenciesInteractor: CurrenciesInteractor) : ViewModel() {

    val snackbarMessage = MutableLiveData<String>()
    val currencies = MutableLiveData<List<CurrencyViewModel>>()

    fun getCurrencies(lifecycleOwner: LifecycleOwner, currency: String = DEFAULT_CURRENCY) {
        currenciesInteractor.getCurrencies(currency).observe(lifecycleOwner, Observer { value ->
            value.error?.let { error ->
                snackbarMessage.value = error.message
                return@Observer
            }
            val currencyViewModels = mutableListOf<CurrencyViewModel>()
            value.selectedCurrency?.let {
                currencyViewModels.add(CurrencyViewModel(it, DEFAULT_RATE_VALUE, isSelected = true))
            }
            value.currencies?.let {
                currencyViewModels.addAll(it)
            }
            currencies.value = currencyViewModels
        })
    }

    companion object{
        private const val DEFAULT_CURRENCY = "EUR"
        private const val DEFAULT_RATE_VALUE = 1f
    }
}