package com.cya.frame.demo.ui.main

import com.cya.frame.demo.BuildConfig
import com.cya.frame.demo.base.DemoBaseActivity
import com.cya.frame.demo.databinding.ActivityMainBinding
import com.cya.frame.ext.versionName
import com.orhanobut.logger.Logger

class MainActivity : DemoBaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initData() {
        Logger.e("baseUrl:${BuildConfig.BASE_URL}")
        Logger.e("版本号:${versionName}")
    }

    override fun initView() {
    }

}