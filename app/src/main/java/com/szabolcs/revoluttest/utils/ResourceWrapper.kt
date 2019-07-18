package com.szabolcs.revoluttest.utils

import android.content.Context
import androidx.annotation.StringRes

class ResourceWrapper(private val context: Context) {

    fun getString(@StringRes resourceId: Int): String = context.getString(resourceId)
}