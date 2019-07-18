package com.szabolcs.revoluttest.data.networking

import com.szabolcs.revoluttest.data.model.CurrenciesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("latest")
    fun getCurrencies(@Query("base") base: String): Call<CurrenciesResponse>
}