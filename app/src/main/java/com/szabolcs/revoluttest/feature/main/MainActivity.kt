package com.szabolcs.revoluttest.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.szabolcs.revoluttest.MainBinding
import com.szabolcs.revoluttest.R
import com.szabolcs.revoluttest.feature.main.adapter.CurrencyAdapter
import com.szabolcs.revoluttest.feature.main.adapter.ItemClickListener
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val adapter = CurrencyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<MainBinding>(this, R.layout.activity_main).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
            it.recyclerView.adapter = adapter
        }

        viewModel.currencies.observe(this, Observer {
            adapter.updateItems(it)
        })

        viewModel.snackbarMessage.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        })

        adapter.itemClickListener = object : ItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Snackbar.make(binding.root, "Position: $position ", Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.getCurrencies(this)
    }
}
