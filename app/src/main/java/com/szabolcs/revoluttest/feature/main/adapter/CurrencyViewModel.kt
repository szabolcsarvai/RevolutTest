package com.szabolcs.revoluttest.feature.main.adapter

import android.text.Editable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyViewModel(val currency: String, val displayName: String, var rate: BigDecimal,  isSelected: Boolean = false) {

    var currencyChangeListener: CurrencyValueChangeListener? = null

    val isSelected = ObservableBoolean(isSelected)
    val currentRate = ObservableField(rate.toString())

    fun updateRate(newRate: BigDecimal, newValue: String) {
        rate = newRate
        if (!isSelected.get()) {
            setSelectedCurrency(newValue)
        }
    }

    fun setSelectedCurrency(newValue: String) {
        currentRate.set(if (newValue.isEmpty()) "" else calculateRate(BigDecimal(newValue), rate))
    }

    fun afterTextChanged(s: Editable?) {
        if (isSelected.get()) {
            currencyChangeListener?.onValueChanged(s.toString(), rate)
            if (!s.isNullOrEmpty()) {
                currentRate.set(s.toString())
            }
        }
    }

    private fun calculateRate(value: BigDecimal, rate: BigDecimal) =
        (value * rate).setScale(2, RoundingMode.CEILING).toString()
}
