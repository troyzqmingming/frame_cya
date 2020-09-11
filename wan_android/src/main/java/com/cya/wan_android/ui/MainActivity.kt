package com.cya.wan_android.ui

import androidx.fragment.app.Fragment
import com.cya.wan_android.R
import com.cya.wan_android.base.CyaBaseActivity
import com.cya.wan_android.databinding.ActivityMainBinding

class MainActivity : CyaBaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun onBackPressed() {
        val mMainNavFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.fragment_container)
        val fragment =
            mMainNavFragment?.childFragmentManager?.primaryNavigationFragment
        if (fragment is MainFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun setObserve() {
    }
}
