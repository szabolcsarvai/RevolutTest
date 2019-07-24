package com.szabolcs.revoluttest.feature.main

import android.text.Editable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyViewModel(val currency: String, val rate: BigDecimal, isSelected: Boolean = false) {

    val isSelected = ObservableBoolean(isSelected)
    val currentRate = ObservableField(rate.toString())

    var currencyChangeListener: CurrencyValueChangeListener? = null

    fun setSelectedCurrency(newValue: String, selectedCurrencyRate: BigDecimal) {
        if (isSelected.get()) {
            currentRate.set(newValue)
        } else {
            currentRate.set(if (newValue.isEmpty()) "" else calculateRate(BigDecimal(newValue), rate, selectedCurrencyRate))
        }
    }

    fun afterTextChanged(s: Editable?) {
        if (isSelected.get()) {
            currencyChangeListener?.onValueChanged(s.toString(), rate)
        }
    }

    private fun calculateRate(value: BigDecimal, rate: BigDecimal, selectedCurrencyRate: BigDecimal) =
        ((value * rate) / selectedCurrencyRate).setScale(2, RoundingMode.CEILING).toString()
}
