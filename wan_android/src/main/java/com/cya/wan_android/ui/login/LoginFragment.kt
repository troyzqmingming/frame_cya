package com.cya.wan_android.ui.login

import com.cya.frame.ext.clickNoRepeat
import com.cya.wan_android.base.CyaBaseVMFragment
import com.cya.wan_android.databinding.FragmentLoginBinding
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import org.koin.android.viewmodel.ext.android.getViewModel

class LoginFragment : CyaBaseVMFragment<FragmentLoginBinding, LoginVM>() {
    override fun getViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.btnLogin.clickNoRepeat {
            vm.login(binding.etPhone.text.toString(), binding.etPassword.text.toString())
        }
    }

    override fun initData() {
    }

    override fun setObserve() {
        vm.apply {
            userLiveData.observe(this@LoginFragment) {
                Logger.e("登录结果:${Gson().toJson(it)}")
            }
        }
    }

    override fun initViewModel(): LoginVM {
        return getViewModel()
    }

}