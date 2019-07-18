package com.szabolcs.revoluttest.data.di

import com.szabolcs.revoluttest.data.interactor.CurrenciesInteractor
import com.szabolcs.revoluttest.data.repository.CurrenciesRepositoryImpl
import com.szabolcs.revoluttest.feature.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val featureModule = module {
    viewModel { MainViewModel(get()) }
}

val interactorModule = module {
    single { CurrenciesInteractor(get()) }
}

val repositoryModule = module {
    single { CurrenciesRepositoryImpl() }
}