package com.cya.wan_android.ui

import com.cya.wan_android.base.CyaBaseFragment
import com.cya.wan_android.databinding.FragmentFlashBinding
import com.cya.wan_android.ext.nav
import java.util.*
import kotlin.concurrent.timerTask

class FlashFragment : CyaBaseFragment<FragmentFlashBinding>() {

    override fun getViewBinding(): FragmentFlashBinding {
        return FragmentFlashBinding.inflate(layoutInflater)
    }

    override fun initView() {
    }

    override fun initData() {
        Timer().schedule(timerTask {
            nav(FlashFragmentDirections.actionFlashFragmentToMainFragment())
        }, 1000L)
    }

    override fun setObserve() {
    }
}