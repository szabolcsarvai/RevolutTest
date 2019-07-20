package com.szabolcs.revoluttest.feature.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(viewModel: T)
}