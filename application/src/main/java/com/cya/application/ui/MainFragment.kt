package com.cya.application.ui

import com.cya.application.R
import com.cya.application.databinding.FragmentMainBinding
import com.cya.frame.ext.clickNoRepeat
import com.cya.library_base.ext.nav
import com.cya.library_base.ui.CYABaseFragment

class MainFragment : CYABaseFragment<FragmentMainBinding>() {
    override fun getViewBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.btnLogin.clickNoRepeat {
            nav(R.id.action_global_loginFragment)
        }
    }

    override fun initData() {
    }

}