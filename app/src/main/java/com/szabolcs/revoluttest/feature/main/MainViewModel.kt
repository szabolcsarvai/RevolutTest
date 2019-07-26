package com.szabolcs.revoluttest.feature.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.szabolcs.revoluttest.data.interactor.CurrenciesInteractor
import com.szabolcs.revoluttest.data.model.CurrenciesResultState
import com.szabolcs.revoluttest.feature.main.adapter.CurrencyViewModel
import kotlinx.coroutines.*
import java.math.BigDecimal

class MainViewModel(private val currenciesInteractor: CurrenciesInteractor) : ViewModel() {

    val snackbarMessage = MutableLiveData<String>()
    val currencies = MutableLiveData<List<CurrencyViewModel>>()
    private var coroutineTimer: Job? = null
    private var shouldLoad = true
    var selectedCurrency = DEFAULT_CURRENCY

    fun startLoading(lifecycleOwner: LifecycleOwner) {
        startCoroutine { getCurrencies(lifecycleOwner) }
    }

    fun getCurrencies(lifecycleOwner: LifecycleOwner, selectedCurrency: String){
        this.selectedCurrency = selectedCurrency
        getCurrencies(lifecycleOwner)
    }

    private fun getCurrencies(lifecycleOwner: LifecycleOwner) {
        if (shouldLoad) {
            CoroutineScope(Dispatchers.Main).launch {
                currenciesInteractor.getCurrencies(selectedCurrency)
                    .observe(lifecycleOwner, Observer { response ->
                        handleResponse(response)
                    })
            }
        }
    }

    private fun startCoroutine(repeatMillis: Long = 1000, action: () -> Unit) {
        coroutineTimer = GlobalScope.launch {
            while (true) {
                action()
                delay(repeatMillis)
            }
        }
    }

    fun onPause() {
        shouldLoad = false
    }

    fun onResume() {
        shouldLoad = true
    }

    fun cancelTimer() {
        coroutineTimer?.cancel()
        coroutineTimer = null
    }

    private fun handleResponse(response: CurrenciesResultState) {
        response.error?.let { error ->
            cancelTimer()
            snackbarMessage.value = error.message
            return
        }
        val currencyViewModels = mutableListOf<CurrencyViewModel>()
        response.selectedCurrency?.let {
            currencyViewModels.add(
                CurrencyViewModel(
                    it,
                    BigDecimal(DEFAULT_RATE_VALUE),
                    isSelected = true
                )
            )
        }
        response.currencies?.let {
            currencyViewModels.addAll(it)
        }
        currencies.value = currencyViewModels
    }

    override fun onCleared() {
        super.onCleared()
        cancelTimer()
    }

    companion object {
        private const val DEFAULT_CURRENCY = "EUR"
        private const val DEFAULT_RATE_VALUE = "1.0"
    }
}