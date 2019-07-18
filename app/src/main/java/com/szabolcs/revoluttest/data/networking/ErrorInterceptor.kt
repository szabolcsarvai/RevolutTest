package com.szabolcs.revoluttest.data.networking

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.szabolcs.revoluttest.R
import com.szabolcs.revoluttest.utils.ResourceWrapper
import okhttp3.Interceptor
import okhttp3.Response
import java.net.UnknownHostException

class ErrorInterceptor(private val resourceWrapper: ResourceWrapper, private val gson: Gson) : Interceptor {

    @Throws(ServiceError::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response: Response

        try {
            response = chain.proceed(request)
            if (!response.isSuccessful) {
                val errorString = response.body()?.string()
                gson.fromJson(errorString, ErrorObject::class.java).also {
                    throw ServiceError(it.error, response.code())
                }
            }
        } catch (exception: UnknownHostException) {
            throw ServiceError(resourceWrapper.getString(R.string.no_network), errorCode = NETWORK_ERROR)
        } catch (illegal: IllegalStateException) {
            throw ServiceError(resourceWrapper.getString(R.string.something_went_wrong), errorCode = NETWORK_ERROR)
        }
        return response
    }

    companion object {
        private const val NETWORK_ERROR = 1
    }

    private data class ErrorObject(@SerializedName("error") val error: String)
}