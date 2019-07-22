package com.szabolcs.revoluttest.feature.main

import android.text.Editable
import androidx.databinding.ObservableFloat

class CurrencyViewModel(val currency: String, val rate: Float, var isSelected: Boolean = false) {

    val currentRate = ObservableFloat(rate)

    var currencyChangeListener: CurrencyValueChangeListener? = null

    fun setSelectedCurrency(newValue: Float) {
        if (isSelected) {
            currentRate.set(newValue)
        } else {
            currentRate.set(calculateRate(newValue, rate))
        }
    }

    fun afterTextChanged(s: Editable?) {
        if (isSelected) {
            currencyChangeListener?.onValueChanged((s.toString().toFloat()))
        }
    }

    private fun calculateRate(value: Float, rate: Float) = "%.2f".format(value * rate).toFloat()
}
