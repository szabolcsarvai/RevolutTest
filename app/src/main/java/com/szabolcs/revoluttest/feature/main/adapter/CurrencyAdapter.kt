package com.szabolcs.revoluttest.feature.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.szabolcs.revoluttest.R

class CurrencyAdapter : RecyclerView.Adapter<ViewHolder<CurrencyViewModel>>(), VM2.ValueChangeListener {

    private var itemViewModels = mutableListOf<CurrencyViewModel>()
    private val typeFactory = TypeFactoryImpl()

    var itemClickListener: ItemClickListener? = null

    fun updateItems(newItems: List<CurrencyViewModel>) {
        val selectedValue: VM2 = newItems[0] as VM2
        selectedValue.valueListener = this
        val diffUtil = DiffUtil.calculateDiff(CurrencyDiffUtilCallback(itemViewModels, newItems))
        itemViewModels.clear()
        itemViewModels.addAll(newItems)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<CurrencyViewModel> {
        return typeFactory.holder(TypeFactoryImpl.Types.values()[viewType], parent, itemClickListener) as ViewHolder<CurrencyViewModel>
    }

    override fun getItemViewType(position: Int): Int {
        return itemViewModels[position].getType(typeFactory).ordinal
    }

    override fun getItemCount() = itemViewModels.size

    override fun onBindViewHolder(holder: ViewHolder<CurrencyViewModel>, position: Int) {
        holder.bind(itemViewModels[position])
    }

    override fun onValueChanged(newValue: Float) {
        for (i in 1 until itemCount) {
            (itemViewModels[i] as VM1).apply {
                currentRate.set(calculateRate(newValue, rate))
            }
        }
    }

    private fun calculateRate(value : Float, rate: Float) = "%2f".format(value * rate).toFloat()
}