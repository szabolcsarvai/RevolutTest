package com.szabolcs.revoluttest.utils

import com.szabolcs.revoluttest.data.model.CurrenciesResponseState
import com.szabolcs.revoluttest.data.model.CurrenciesResultState
import com.szabolcs.revoluttest.feature.CurrencyViewModel

val transformResponse: (CurrenciesResponseState) -> CurrenciesResultState = {
    CurrenciesResultState(
        selectedCurrency = it.selectedCurrency,
        currencies = it.currencies?.map { item ->
            CurrencyViewModel(item.key, item.value)
        },
        isLoading = it.isLoading,
        error = it.error
    )
}