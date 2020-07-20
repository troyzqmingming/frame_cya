package com.cya.frame.demo.ui.main

import com.cya.frame.demo.base.DemoBaseActivity
import com.cya.frame.demo.databinding.ActivityMainBinding

class MainActivity : DemoBaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initData() {
    }

    override fun initView() {
    }

}