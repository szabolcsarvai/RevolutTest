package com.szabolcs.revoluttest.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.szabolcs.revoluttest.CurrencyItemBinding
import com.szabolcs.revoluttest.R

class CurrencyViewHolder(private val binding: CurrencyItemBinding, val clickListener: ItemClickListener?) : ViewHolder<VM1>(binding.root) {

    override fun bind(viewModel: VM1) {
        binding.viewModel = viewModel
    }

    companion object {
        fun create(parent: ViewGroup, itemClickListener: ItemClickListener?) =
            CurrencyViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_currency, parent, false),
                itemClickListener
            ).apply {
                binding.value.setOnClickListener {
                    clickListener?.onItemClick(it, adapterPosition)
                }
            }
    }
}