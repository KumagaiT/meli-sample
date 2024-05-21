package com.kumagai.melisample

import android.app.Application
import com.kumagai.melisample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MeliApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MeliApplication)
            modules(appModule)
        }
    }
}