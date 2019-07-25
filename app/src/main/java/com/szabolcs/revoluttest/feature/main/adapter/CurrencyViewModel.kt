package com.szabolcs.revoluttest.feature.main.adapter

import android.text.Editable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyViewModel(val currency: String, var rate: BigDecimal, isSelected: Boolean = false) {

    var currencyChangeListener: CurrencyValueChangeListener? = null
    private var selectedCurrencyRate = BigDecimal("1")
    private var selectedCurrencyValue = "1"

    val isSelected = ObservableBoolean(isSelected)
    val currentRate = ObservableField(rate.toString())

    fun updateRate(newRate: BigDecimal) {
        rate = newRate
        setSelectedCurrency(selectedCurrencyValue, selectedCurrencyRate)
    }

    fun setSelectedCurrency(newValue: String, selectedCurrencyRate: BigDecimal) {
        selectedCurrencyValue = newValue
        this.selectedCurrencyRate = selectedCurrencyRate

        if (isSelected.get()) {
            currentRate.set(newValue)
        } else {
            currentRate.set(if (newValue.isEmpty()) "" else calculateRate(BigDecimal(newValue), rate, selectedCurrencyRate))
        }
    }

    fun afterTextChanged(s: Editable?) {
        if (isSelected.get()) {
            currencyChangeListener?.onValueChanged(s.toString(), rate)
            if(!s.isNullOrEmpty()) {
                selectedCurrencyValue = s.toString()
                currentRate.set(s.toString())
            }
        }
    }

    private fun calculateRate(value: BigDecimal, rate: BigDecimal, selectedCurrencyRate: BigDecimal) =
        ((value * rate) / selectedCurrencyRate).setScale(2, RoundingMode.CEILING).toString()
}
