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
        //获取hostFragment
        val mMainNavFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.fragment_container)
        //获取当前所在的fragment
        val fragment =
            mMainNavFragment?.childFragmentManager?.primaryNavigationFragment
        //如果当前处于根fragment即HostFragment
        if (fragment is MainFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
