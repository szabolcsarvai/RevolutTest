package com.szabolcs.revoluttest

import android.app.Application
import com.szabolcs.revoluttest.data.di.*
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
                    networkingModule,
                    wrapperModule
                )
            )
        }
    }
}