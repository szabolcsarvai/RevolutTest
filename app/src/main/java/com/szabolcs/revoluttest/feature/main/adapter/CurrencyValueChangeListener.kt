package com.szabolcs.revoluttest.feature.main.adapter

import java.math.BigDecimal

interface CurrencyValueChangeListener {
    fun onValueChanged(newValue: String, selectedCurrencyRate: BigDecimal)
}