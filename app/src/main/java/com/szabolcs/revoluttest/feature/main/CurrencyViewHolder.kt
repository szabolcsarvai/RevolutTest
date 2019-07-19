package com.szabolcs.revoluttest.feature.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.szabolcs.revoluttest.CurrencyItemBinding
import com.szabolcs.revoluttest.R

class CurrencyViewHolder(private val binding: CurrencyItemBinding, clickListener: ItemClickListener?) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: CurrencyViewModel) {
        binding.viewModel = viewModel
    }

    companion object {
        fun create(parent: ViewGroup, itemClickListener: ItemClickListener?) =
            CurrencyViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.currency_item, parent, false),
                itemClickListener
            )
    }
}