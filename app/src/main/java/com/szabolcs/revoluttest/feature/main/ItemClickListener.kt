package com.szabolcs.revoluttest.feature.main

import android.view.View

interface ItemClickListener {
    fun onItemClick(view: View, position: Int)
}