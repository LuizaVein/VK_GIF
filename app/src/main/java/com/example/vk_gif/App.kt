package com.example.vk_gif

import android.app.Application
import com.example.vk_gif.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        setUpKoin()
    }

    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setUpKoin() {
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@App)
            // Load modules
            modules(appModule)
        }
    }
}