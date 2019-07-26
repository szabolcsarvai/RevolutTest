package com.szabolcs.revoluttest.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.szabolcs.revoluttest.MainBinding
import com.szabolcs.revoluttest.R
import com.szabolcs.revoluttest.feature.main.adapter.CurrencyAdapter
import com.szabolcs.revoluttest.feature.main.adapter.CurrencyItemSelectedListener
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

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.cancelTimer()
            viewModel.startLoading(this@MainActivity)
        }

        adapter.currencyItemSelectedListener = object : CurrencyItemSelectedListener {
            override fun onItemSelected(position: Int) {
                if (position == 0) return
                viewModel.getCurrencies(this@MainActivity, adapter.getItem(position).currency)
                adapter.switchItems(position)
            }
        }

        viewModel.currencies.observe(this, Observer {
            adapter.handleUpdate(it)
        })

        viewModel.snackbarMessage.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.swipeToRefresh.isRefreshing = isLoading
        })

        viewModel.startLoading(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelTimer()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}
