package com.szabolcs.revoluttest.feature.main

data class CurrencyViewModel(val currency: String, val rate: Float, var isSelected: Boolean? = false)