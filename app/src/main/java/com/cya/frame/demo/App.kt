package com.cya.frame.demo

import android.app.Application
import android.content.Context
import com.cya.frame.CyaSDK
import com.cya.frame.demo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        CyaSDK.initApplication()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}