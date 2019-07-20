package com.szabolcs.revoluttest.feature.main.adapter

import android.text.Editable
import androidx.databinding.ObservableFloat

class VM2(override val currency: String) : CurrencyViewModel {

    interface ValueChangeListener {
        fun onValueChanged(newValue: Float)
    }

    var valueListener: ValueChangeListener? = null

    override fun getType(typeFactory: TypeFactory) = typeFactory.getType(this)

    fun afterTextChanged(s: Editable?) {
        valueListener?.onValueChanged((s.toString().toFloat()))
    }

    val value = ObservableFloat(100f)
}