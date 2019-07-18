package com.szabolcs.revoluttest.data.di

import com.google.gson.GsonBuilder
import com.szabolcs.revoluttest.data.interactor.CurrenciesInteractor
import com.szabolcs.revoluttest.data.networking.NetworkingManager
import com.szabolcs.revoluttest.data.repository.CurrenciesRepositoryImpl
import com.szabolcs.revoluttest.feature.MainViewModel
import com.szabolcs.revoluttest.utils.ResourceWrapper
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { MainViewModel(get()) }
}

val interactorModule = module {
    single { CurrenciesInteractor(get()) }
}

val repositoryModule = module {
    single { CurrenciesRepositoryImpl(get()) }
}

val networkingModule = module {
    single { GsonBuilder().create() }
    factory { NetworkingManager(get(), get()) }
}

val wrapperModule = module {
    single { ResourceWrapper(androidContext()) }
}