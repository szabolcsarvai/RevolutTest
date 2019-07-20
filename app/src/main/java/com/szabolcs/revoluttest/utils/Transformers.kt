package com.szabolcs.revoluttest.utils

import com.szabolcs.revoluttest.data.model.CurrenciesResponseState
import com.szabolcs.revoluttest.data.model.CurrenciesResultState
import com.szabolcs.revoluttest.feature.main.adapter.VM1

val transformResponse: (CurrenciesResponseState) -> CurrenciesResultState = {
    CurrenciesResultState(
        selectedCurrency = it.selectedCurrency,
        currencies = it.currencies?.map { item ->
            VM1(item.key, item.value)
        },
        isLoading = it.isLoading,
        error = it.error
    )
}