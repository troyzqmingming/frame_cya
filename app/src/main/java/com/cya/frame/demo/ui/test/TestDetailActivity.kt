package com.cya.frame.demo.ui.test

import androidx.navigation.ActivityNavigator
import com.cya.frame.demo.base.DemoBaseActivity
import com.cya.frame.demo.databinding.ActivityTestDetailBinding

class TestDetailActivity : DemoBaseActivity<ActivityTestDetailBinding>() {

    override fun getViewBinding(): ActivityTestDetailBinding =
        ActivityTestDetailBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initData() {
    }

    override fun finish() {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this);
    }
}