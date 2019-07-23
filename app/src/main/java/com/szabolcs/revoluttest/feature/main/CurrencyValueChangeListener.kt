package com.szabolcs.revoluttest.feature.main

interface CurrencyValueChangeListener {
    fun onValueChanged(newValue: Float, selectedCurrencyRate: Float)
}