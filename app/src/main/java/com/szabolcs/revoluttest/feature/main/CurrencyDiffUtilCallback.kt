package com.szabolcs.revoluttest.feature.main

import androidx.recyclerview.widget.DiffUtil

class CurrencyDiffUtilCallback(
    private val oldList: List<CurrencyViewModel>,
    private val newList: List<CurrencyViewModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].currency == newList[newItemPosition].currency

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].currency == newList[newItemPosition].currency &&
                oldList[oldItemPosition].rate == newList[newItemPosition].rate

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}