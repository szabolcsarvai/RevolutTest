package com.szabolcs.revoluttest.utils

import com.szabolcs.revoluttest.data.model.CurrenciesResponseState
import com.szabolcs.revoluttest.data.model.CurrenciesResultState
import com.szabolcs.revoluttest.feature.main.CurrencyViewModel
import java.math.BigDecimal

val transformResponse: (CurrenciesResponseState) -> CurrenciesResultState = {
    CurrenciesResultState(
        selectedCurrency = it.selectedCurrency,
        currencies = it.currencies?.map { item ->
            CurrencyViewModel(item.key, BigDecimal(item.value))
        },
        isLoading = it.isLoading,
        error = it.error
    )
}