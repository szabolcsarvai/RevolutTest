package com.szabolcs.revoluttest.data.networking

import java.io.IOException

data class ServiceError(override val message: String, val errorCode: Int = 0) : IOException()