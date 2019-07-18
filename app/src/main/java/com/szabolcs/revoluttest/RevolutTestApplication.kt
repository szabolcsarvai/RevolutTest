package com.szabolcs.revoluttest

import android.app.Application
import com.szabolcs.revoluttest.data.di.featureModule
import com.szabolcs.revoluttest.data.di.interactorModule
import com.szabolcs.revoluttest.data.di.networkingModule
import com.szabolcs.revoluttest.data.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RevolutTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RevolutTestApplication)
            modules(
                listOf(
                    featureModule,
                    interactorModule,
                    repositoryModule,
                    networkingModule
                )
            )
        }
    }
}