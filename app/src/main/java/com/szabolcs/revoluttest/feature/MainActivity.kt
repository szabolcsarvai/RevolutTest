package com.szabolcs.revoluttest.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.szabolcs.revoluttest.MainBinding
import com.szabolcs.revoluttest.R
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<MainBinding>(this, R.layout.activity_main).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
    }
}
