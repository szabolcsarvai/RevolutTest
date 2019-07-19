package com.szabolcs.revoluttest.feature.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {

    private var itemViewModels = mutableListOf<CurrencyViewModel>()

    var itemClickListener: ItemClickListener? = null

    fun updateItems(newItems: List<CurrencyViewModel>) {
        val diffUtil = DiffUtil.calculateDiff(CurrencyDiffUtilCallback(itemViewModels, newItems))
        itemViewModels.clear()
        itemViewModels.addAll(newItems)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyViewHolder.create(parent, itemClickListener)

    override fun getItemCount() = itemViewModels.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(itemViewModels[position])
    }
}