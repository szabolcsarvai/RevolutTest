package com.szabolcs.revoluttest.data.model

import com.szabolcs.revoluttest.data.networking.ServiceError

open class DataState(
    val isLoading: Boolean? = false,
    val error: ServiceError? = null
)