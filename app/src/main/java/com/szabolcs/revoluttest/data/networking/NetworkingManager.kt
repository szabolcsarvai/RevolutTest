package com.szabolcs.revoluttest.data.networking

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkingManager(gson: Gson){

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://revolut.duckdns.org/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val service: CurrencyService = retrofit.create(CurrencyService::class.java)
}