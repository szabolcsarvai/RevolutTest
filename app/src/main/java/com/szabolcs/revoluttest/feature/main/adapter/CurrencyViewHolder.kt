package com.szabolcs.revoluttest.feature.main.adapter

import android.annotation.SuppressLint
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.szabolcs.revoluttest.CurrencyItemBinding
import android.view.*
import com.szabolcs.revoluttest.R


class CurrencyViewHolder(private val binding: CurrencyItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: CurrencyViewModel) {
        binding.viewModel = viewModel
    }

    companion object {
        @SuppressLint("ClickableViewAccessibility")
        fun create(parent: ViewGroup, currencyItemSelectedListener: CurrencyItemSelectedListener?) =
            CurrencyViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.currency_item,
                    parent,
                    false
                )
            ).also { viewHolder ->
                viewHolder.binding.value.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_UP) {
                        currencyItemSelectedListener?.onItemSelected(viewHolder.adapterPosition)
                    }
                    false
                }
            }
    }
}