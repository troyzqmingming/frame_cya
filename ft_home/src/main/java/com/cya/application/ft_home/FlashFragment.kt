package com.cya.application.ft_home

import com.cya.application.ft_home.databinding.FragmentFlashBinding
import com.cya.lib_base.base.CyaBaseFragment
import com.cya.lib_base.ext.nav
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
            nav(R.id.action_flashFragment_to_mainFragment)
        }, 1000L)
    }

    override fun setObserve() {
    }
}