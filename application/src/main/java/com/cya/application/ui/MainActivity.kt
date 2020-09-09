package com.cya.application.ui

import com.cya.application.databinding.ActivityMainBinding
import com.cya.library_base.ui.CYABaseActivity

class MainActivity : CYABaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
    }

    override fun initData() {
    }
}
