package com.cya.application

import android.app.Application
import com.cya.frame.CyaSDK
import com.cya.library_base.di.getAllModuleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        CyaSDK.initApplication(isLogger = true)
        startKoin {
            androidContext(this@App)
            modules(getAllModuleList())
        }
    }

}