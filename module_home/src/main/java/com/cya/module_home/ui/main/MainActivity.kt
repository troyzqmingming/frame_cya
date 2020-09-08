package com.cya.module_home.ui.main

import com.cya.lib_base.ui.CYABaseActivity
import com.cya.module_home.databinding.ActivityMainBinding

class MainActivity : CYABaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initData() {
    }

    override fun initView() {
    }
}