package com.szabolcs.revoluttest.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.szabolcs.revoluttest.R
import com.szabolcs.revoluttest.SelectedCurrencyItemBinding

class SelectedCurrencyViewHolder(private val binding: SelectedCurrencyItemBinding) : ViewHolder<VM2>(binding.root) {

    override fun bind(viewModel: VM2) {
        binding.viewModel = viewModel
    }

    companion object {
        fun create(parent: ViewGroup) =
            SelectedCurrencyViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_selected_currency, parent, false)
            )
    }
}