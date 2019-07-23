package com.szabolcs.revoluttest.feature.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter : RecyclerView.Adapter<CurrencyViewHolder>(), CurrencyValueChangeListener {

    private var itemViewModels = mutableListOf<CurrencyViewModel>()

    var currencyItemSelectedListener = object : CurrencyItemSelectedListener {
        override fun onItemSelected(position: Int) {
            switchItems(position)
        }
    }

   private fun switchItems(pos: Int) {
        val firstItem = itemViewModels[0]
        firstItem.currencyChangeListener = null
        firstItem.isSelected.set(false)
        val selectedItem = itemViewModels[pos]
        selectedItem.currencyChangeListener = this
        selectedItem.isSelected.set(true)
        itemViewModels[0] = selectedItem
        itemViewModels[pos] = firstItem
        notifyItemChanged(0)
        notifyItemChanged(pos)
    }

    fun updateItems(newItems: List<CurrencyViewModel>) {
        newItems[0].currencyChangeListener = this

        val diffUtil = DiffUtil.calculateDiff(CurrencyDiffUtilCallback(itemViewModels, newItems))
        diffUtil.dispatchUpdatesTo(this)
        itemViewModels.clear()
        itemViewModels.addAll(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyViewHolder.create(parent, currencyItemSelectedListener)

    override fun getItemCount() = itemViewModels.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(itemViewModels[position])
    }

    override fun onValueChanged(newValue: Float, selectedCurrencyRate: Float) {
        for (itemViewModel in itemViewModels) {
            itemViewModel.setSelectedCurrency(newValue, selectedCurrencyRate)
        }
    }
}