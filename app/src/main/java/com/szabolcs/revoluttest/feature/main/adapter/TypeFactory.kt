package com.szabolcs.revoluttest.feature.main.adapter

import android.view.ViewGroup

interface TypeFactory {

    fun getType(viewModel: VM2): TypeFactoryImpl.Types

    fun getType(viewModel: VM1): TypeFactoryImpl.Types

    fun holder(type: TypeFactoryImpl.Types, parent: ViewGroup, itemClickListener: ItemClickListener?): ViewHolder<*>
}