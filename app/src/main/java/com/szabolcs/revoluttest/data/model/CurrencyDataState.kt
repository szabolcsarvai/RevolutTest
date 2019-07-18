package com.szabolcs.revoluttest.data.model

data class CurrencyDataState<T>(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String?,
    val currencies: List<T>?
)