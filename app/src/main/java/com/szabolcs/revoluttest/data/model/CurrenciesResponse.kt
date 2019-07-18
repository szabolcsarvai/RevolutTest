package com.szabolcs.revoluttest.data.model

import com.google.gson.annotations.SerializedName

data class CurrenciesResponse(
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Map<String, Float>
)