package com.cya.application.ui.me

import com.cya.application.databinding.FragmentMineBinding
import com.cya.application.router.navLogin
import com.cya.frame.ext.clickNoRepeat
import com.cya.library_base.ui.CYABaseFragment

class MineFragment : CYABaseFragment<FragmentMineBinding>() {
    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.btnLogin.clickNoRepeat {
            navLogin()
        }
    }

    override fun initData() {
    }

}