package com.szabolcs.revoluttest.utils

import com.szabolcs.revoluttest.data.model.CurrenciesResponseState
import com.szabolcs.revoluttest.data.model.CurrenciesResultState
import com.szabolcs.revoluttest.feature.main.adapter.CurrencyViewModel
import java.math.BigDecimal
import java.util.Currency

val transformResponse: (CurrenciesResponseState) -> CurrenciesResultState = {
    CurrenciesResultState(
        selectedCurrency = it.selectedCurrency,
        currencies = it.currencies?.map { item ->
            CurrencyViewModel(item.key, Currency.getInstance(item.key).displayName, BigDecimal(item.value))
        },
        error = it.error
    )
}