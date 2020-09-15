package com.cya.myapplication

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.cya.frame.CyaSDK
import com.cya.myapplication.di.appModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ARouter.openDebug()
        ARouter.openLog()
        ARouter.init(this)
        CyaSDK.initApplication()
        startKoin {
            modules(appModule)
        }
    }

}