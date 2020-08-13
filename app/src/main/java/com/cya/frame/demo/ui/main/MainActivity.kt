package com.cya.frame.demo.ui.main

import androidx.fragment.app.Fragment
import com.cya.frame.demo.BuildConfig
import com.cya.frame.demo.R
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

    override fun onBackPressed() {
        //获取hostFragment
        val mMainNavFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.navigation_view)
        //获取当前所在的fragment
        val fragment =
            mMainNavFragment?.childFragmentManager?.primaryNavigationFragment
        //如果当前处于根fragment即HostFragment
        if (fragment is MainFragment) {
            //Activity退出但不销毁
            moveTaskToBack(false)
        }else{
            super.onBackPressed()
        }
    }
}