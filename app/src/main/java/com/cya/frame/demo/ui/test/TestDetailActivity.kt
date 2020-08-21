package com.cya.frame.demo.ui.test

import androidx.core.view.ViewCompat
import androidx.navigation.ActivityNavigator
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseActivity
import com.cya.frame.demo.databinding.ActivityTestDetailBinding

class TestDetailActivity : DemoBaseActivity<ActivityTestDetailBinding>() {

    override fun getViewBinding(): ActivityTestDetailBinding =
        ActivityTestDetailBinding.inflate(layoutInflater)

    override fun initView() {
        ViewCompat.setTransitionName(
            binding.imageView,
            getString(R.string.share_element_test_image)
        )
    }

    override fun initData() {
    }

    override fun finish() {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this);
    }
}