package com.example.movieme

import android.app.Application
import com.example.movieme.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    dataModule,
                    serviceModule,
                    okHttpModule,
                    domainModule
                )
            )
        }
    }
}