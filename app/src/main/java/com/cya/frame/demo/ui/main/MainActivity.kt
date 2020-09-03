package com.cya.frame.demo.ui.main

import androidx.fragment.app.Fragment
import com.allenliu.versionchecklib.callback.APKDownloadListener
import com.cya.frame.demo.BuildConfig
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseActivity
import com.cya.frame.demo.databinding.ActivityMainBinding
import com.cya.frame.demo.ext.FILE_PRIVATE_PATH_ROOT
import com.cya.frame.demo.uitls.AppUpdateUtils
import com.cya.frame.ext.versionName
import com.orhanobut.logger.Logger
import java.io.File

class MainActivity : DemoBaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initData() {
        Logger.e("baseUrl:${BuildConfig.BASE_URL}")
        Logger.e("版本号:${versionName}")
        AppUpdateUtils.checkUpdate(
            AppUpdateUtils.UpdateInfo(
                FILE_PRIVATE_PATH_ROOT,
                "new.apk",
                "https://imtt.dd.qq.com/16891/apk/2FA0E483C607FEED12E4C1519DAF834A.apk?fsname=com.dndc.apps.chebaba_1.0.5_2.apk&csr=1bbd"
            ), object : APKDownloadListener {
                override fun onDownloading(progress: Int) {
                }

                override fun onDownloadSuccess(file: File?) {
                }

                override fun onDownloadFail() {
                }

            })
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
        } else {
            super.onBackPressed()
        }
    }
}