package com.cya.wan_android

import android.app.Application
import com.cya.frame.CyaSDK
import com.cya.wan_android.di.appModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        CyaSDK.initApplication()
        startKoin {
            modules(appModule)
        }
    }

}