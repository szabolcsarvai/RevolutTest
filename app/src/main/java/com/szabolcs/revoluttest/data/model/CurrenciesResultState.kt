package com.szabolcs.revoluttest.data.model

import com.szabolcs.revoluttest.data.networking.ServiceError
import com.szabolcs.revoluttest.feature.main.adapter.CurrencyViewModel

class CurrenciesResultState(
    val selectedCurrency: String? = null,
    val currencies: List<CurrencyViewModel>? = null,
    isLoading: Boolean? = false,
    error: ServiceError? = null
) : DataState(isLoading, error)