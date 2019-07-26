package com.szabolcs.revoluttest.feature.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import java.math.BigDecimal


class CurrencyAdapter : RecyclerView.Adapter<CurrencyViewHolder>(), CurrencyValueChangeListener {

    private lateinit var layoutManager: LinearLayoutManager
    private var items = mutableListOf<CurrencyViewModel>()

    var currencyItemSelectedListener: CurrencyItemSelectedListener? = null

    fun handleUpdate(newItems: List<CurrencyViewModel>) {
        if (itemCount == 0) {
            setItems(newItems)
        } else {
            updateItems(newItems)
        }
    }

    private fun restoreScrollPositionAfterAdAdded() {
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (firstVisibleItemPosition == 0) {
            layoutManager.scrollToPosition(0)
        }
    }

    fun switchItems(pos: Int) {
        val item1 = items.removeAt(0)
        item1.isSelected.set(false)
        item1.currencyChangeListener = null

        val item2 = items.removeAt(pos - 1)
        item2.isSelected.set(true)
        item2.currencyChangeListener = this

        items.add(0, item2)
        items.add(pos, item1)

        notifyItemMoved(pos, 0)
        notifyItemMoved(1, pos)
        restoreScrollPositionAfterAdAdded()
    }

    fun getItem(position: Int) = items[position]

    private fun setItems(newItems: List<CurrencyViewModel>) {
        items.addAll(newItems)
        if (itemCount != 0) {
            items[0].currencyChangeListener = this
        }
        notifyDataSetChanged()
    }

    private fun updateItems(newItems: List<CurrencyViewModel>) {
        val selectedItem = items[0]
        for (item in items) {
            for (newItem in newItems) {
                if (item.currency == newItem.currency) {
                    item.updateRate(newItem.rate, selectedItem.currentRate.get()!!)
                    break
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyViewHolder.create(parent, currencyItemSelectedListener)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        layoutManager = recyclerView.layoutManager as LinearLayoutManager
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onValueChanged(newValue: String, selectedCurrencyRate: BigDecimal) {
        for (itemViewModel in items) {
            if (!itemViewModel.isSelected.get()) {
                itemViewModel.setSelectedCurrency(newValue)
            }
        }
    }
}