package com.szabolcs.revoluttest.data.model

import com.szabolcs.revoluttest.data.networking.ServiceError

open class DataState(
    val error: ServiceError? = null
)