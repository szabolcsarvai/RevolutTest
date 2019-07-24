package com.szabolcs.revoluttest.feature.main

import android.annotation.SuppressLint
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.szabolcs.revoluttest.CurrencyItemBinding
import android.view.View.OnTouchListener
import android.util.Log
import android.view.*
import androidx.core.view.MotionEventCompat


class CurrencyViewHolder(
    private val binding: CurrencyItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: CurrencyViewModel) {
        binding.viewModel = viewModel
    }

    companion object {
        @SuppressLint("ClickableViewAccessibility")
        fun create(parent: ViewGroup, currencyItemSelectedListener: CurrencyItemSelectedListener?) =
            CurrencyViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    com.szabolcs.revoluttest.R.layout.currency_item,
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