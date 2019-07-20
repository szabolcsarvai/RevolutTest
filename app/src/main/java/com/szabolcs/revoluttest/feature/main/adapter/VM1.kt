package com.szabolcs.revoluttest.feature.main.adapter

import androidx.databinding.ObservableFloat

class VM1(override val currency: String, val rate: Float) : CurrencyViewModel {

    override fun getType(typeFactory: TypeFactory) = typeFactory.getType(this)

    val currentRate = ObservableFloat()

    init {
        currentRate.set(rate)
    }
}