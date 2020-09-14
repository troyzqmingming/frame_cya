package com.cya.wan_android.ui.login

import com.cya.frame.base.vm.HIDE
import com.cya.frame.base.vm.Loading
import com.cya.frame.base.vm.SHOW
import com.cya.frame.ext.clickNoRepeat
import com.cya.wan_android.base.CyaBaseVMFragment
import com.cya.wan_android.contract.EventKey
import com.cya.wan_android.databinding.FragmentLoginBinding
import com.cya.wan_android.entity.UserResult
import com.cya.wan_android.ext.finish
import com.cya.wan_android.view.ProgressUtil
import com.google.gson.Gson
import com.jeremyliao.liveeventbus.LiveEventBus
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
        binding.tvRegister.clickNoRepeat {
            vm.register(binding.etPhone.text.toString(), binding.etPassword.text.toString())
        }
    }

    override fun initData() {
    }

    override fun setObserve() {
        vm.apply {
            userLiveData.observe(this@LoginFragment) {
                Logger.e("登录结果:${Gson().toJson(it)}")
                finish()
            }
            loadingLiveData.observe(this@LoginFragment) {
                when (it) {
                    is SHOW -> ProgressUtil.show(mActivity, "登录中", false)
                    is HIDE -> ProgressUtil.dismiss(mActivity)
                }
            }
        }
    }

    override fun initViewModel(): LoginVM {
        return getViewModel()
    }

}