package com.szabolcs.revoluttest.data.model

data class CurrenciesResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Float>
)