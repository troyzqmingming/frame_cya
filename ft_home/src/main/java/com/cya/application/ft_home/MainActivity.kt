package com.cya.application.ft_home

import androidx.fragment.app.Fragment
import com.cya.application.ft_home.databinding.ActivityMainBinding
import com.cya.lib_base.base.CyaBaseActivity

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
