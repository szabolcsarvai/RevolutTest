package com.szabolcs.revoluttest

import com.szabolcs.revoluttest.data.networking.ServiceError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.enqueueCall(onSuccess: (T) -> Unit, onFailure: (ServiceError) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            response?.body()?.let {
                onSuccess(it)
            }
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            if (t is ServiceError) {
                onFailure(t)
            }
        }
    })
}