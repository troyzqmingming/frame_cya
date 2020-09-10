package com.cya.application.feature_user.personal

import com.cya.application.feature_user.databinding.FragmentPersonalBinding
import com.cya.application.router.backHome
import com.cya.frame.ext.clickNoRepeat
import com.cya.library_base.ui.CYABaseFragment

class PersonalFragment : CYABaseFragment<FragmentPersonalBinding>() {
    override fun getViewBinding(): FragmentPersonalBinding {
        return FragmentPersonalBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.tvMsg.clickNoRepeat {
            backHome()
        }
    }

    override fun initData() {
    }

}