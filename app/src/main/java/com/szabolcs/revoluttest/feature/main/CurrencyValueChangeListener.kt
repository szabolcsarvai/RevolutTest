package com.szabolcs.revoluttest.feature.main

import java.math.BigDecimal

interface CurrencyValueChangeListener {
    fun onValueChanged(newValue: String, selectedCurrencyRate: BigDecimal)
}