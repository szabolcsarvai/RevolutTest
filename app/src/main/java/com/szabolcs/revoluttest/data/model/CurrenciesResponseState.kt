package com.szabolcs.revoluttest.data.model

import com.szabolcs.revoluttest.data.networking.ServiceError

class CurrenciesResponseState(
    val selectedCurrency: String? = null,
    val currencies: Map<String, Float>? = null,
    isLoading: Boolean? = false,
    error: ServiceError? = null
) : DataState(isLoading, error)