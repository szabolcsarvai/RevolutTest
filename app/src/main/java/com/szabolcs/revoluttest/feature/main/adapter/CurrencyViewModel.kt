package com.szabolcs.revoluttest.feature.main.adapter

interface CurrencyViewModel {

    val currency: String

    fun getType(typeFactory: TypeFactory): TypeFactoryImpl.Types
}