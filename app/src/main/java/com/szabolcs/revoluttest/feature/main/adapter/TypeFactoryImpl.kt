package com.szabolcs.revoluttest.feature.main.adapter

import android.view.ViewGroup
import androidx.lifecycle.ViewModel

class TypeFactoryImpl : TypeFactory {

    override fun holder(type: Types, parent: ViewGroup, itemClickListener: ItemClickListener?): ViewHolder<*> {
        return type.accept(parent, itemClickListener)
    }

    override fun getType(viewModel: VM1) = Types.TYPE_1

    override fun getType(viewModel: VM2) = Types.TYPE_2


    enum class Types {
        TYPE_1 {
            override fun accept(parent: ViewGroup, itemClickListener: ItemClickListener?): ViewHolder<VM1> {
                return CurrencyViewHolder.create(parent, itemClickListener)
            }
        },
        TYPE_2 {
            override fun accept(parent: ViewGroup, itemClickListener: ItemClickListener?): ViewHolder<VM2> {
                return SelectedCurrencyViewHolder.create(parent)
            }

        };

        abstract fun accept(parent: ViewGroup, itemClickListener: ItemClickListener?): ViewHolder<*>
    }
}