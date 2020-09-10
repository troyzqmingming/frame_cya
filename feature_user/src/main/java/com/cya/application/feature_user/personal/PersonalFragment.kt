package com.cya.application.feature_user.personal

import androidx.activity.addCallback
import com.cya.application.feature_user.databinding.FragmentPersonalBinding
import com.cya.application.router.backHome
import com.cya.library_base.ui.CYABaseFragment

class PersonalFragment : CYABaseFragment<FragmentPersonalBinding>() {
    override fun getViewBinding(): FragmentPersonalBinding {
        return FragmentPersonalBinding.inflate(layoutInflater)
    }

    override fun initView() {
        viewLifecycleOwner.apply {
            requireActivity().onBackPressedDispatcher.addCallback {
                backHome()
            }
        }
    }

    override fun initData() {
    }

}