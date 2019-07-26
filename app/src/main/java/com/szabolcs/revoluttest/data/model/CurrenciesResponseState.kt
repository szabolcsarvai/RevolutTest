package com.szabolcs.revoluttest.data.model

import com.szabolcs.revoluttest.data.networking.ServiceError

class CurrenciesResponseState(
    val selectedCurrency: String? = null,
    val currencies: Map<String, String>? = null,
    error: ServiceError? = null
) : DataState(error)