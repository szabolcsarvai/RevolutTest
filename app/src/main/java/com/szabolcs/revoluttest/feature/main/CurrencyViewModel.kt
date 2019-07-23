package com.szabolcs.revoluttest.feature.main

import android.text.Editable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableFloat

class CurrencyViewModel(val currency: String, val rate: Float, isSelected: Boolean = false) {

    val isSelected = ObservableBoolean(isSelected)
    val currentRate = ObservableFloat(rate)

    var currencyChangeListener: CurrencyValueChangeListener? = null

    fun setSelectedCurrency(newValue: Float, selectedCurrencyRate: Float) {
        if (isSelected.get()) {
            currentRate.set(newValue)
        } else {
            currentRate.set(calculateRate(newValue, rate, selectedCurrencyRate))
        }
    }

    fun afterTextChanged(s: Editable?) {
        if (isSelected.get()) {
            currencyChangeListener?.onValueChanged(s.toString().toFloat(), rate)
        }
    }

    private fun calculateRate(value: Float, rate: Float, selectedCurrencyRate: Float) =
        "%.2f".format((value * rate) / selectedCurrencyRate).toFloat()
}
