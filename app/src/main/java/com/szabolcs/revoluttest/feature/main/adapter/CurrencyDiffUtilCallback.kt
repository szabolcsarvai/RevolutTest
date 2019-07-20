package com.szabolcs.revoluttest.feature.main.adapter

import androidx.recyclerview.widget.DiffUtil

class CurrencyDiffUtilCallback(
    private val oldList: List<CurrencyViewModel>,
    private val newList: List<CurrencyViewModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].currency == newList[newItemPosition].currency

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].currency == newList[newItemPosition].currency

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}