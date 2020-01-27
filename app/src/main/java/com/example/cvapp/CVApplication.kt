package com.example.cvapp

import android.app.Application
import com.example.cvapp.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CVApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CVApplication)
            modules(applicationModules)
        }
    }

}