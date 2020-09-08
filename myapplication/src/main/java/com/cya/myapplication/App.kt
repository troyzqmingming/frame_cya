package com.cya.myapplication

import android.app.Application
import android.content.Context
import com.cya.frame.CyaSDK
import com.cya.myapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        CyaSDK.initApplication(isLogger = true)
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}