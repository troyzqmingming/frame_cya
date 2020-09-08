package com.cya.module_user.login

import androidx.lifecycle.Observer
import com.cya.frame.ext.setNoRepeatClick
import com.cya.frame.ext.toast
import com.cya.lib_base.ext.finish
import com.cya.lib_base.ui.CYABaseMVVMFragment
import com.cya.lib_ui.progress.ProgressUtil
import com.cya.module_user.databinding.FragmentLoginBinding
import org.koin.android.viewmodel.ext.android.getViewModel

class LoginFragment : CYABaseMVVMFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun initViewModel(): LoginViewModel {
        return getViewModel()
    }

    override fun getViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun startObserve() {
        super.startObserve()
        vm.apply {
            loginLiveData.observe(this@LoginFragment, Observer {
                toast("登录成功")
                finish()
            })
        }
    }

    override fun initView() {
        setNoRepeatClick(binding.btnLogin, binding.tvRegister) {
            when (it) {
                binding.btnLogin ->
                    vm.loginWanAndroid(
                        binding.etPhone.text.toString().trim(),
                        binding.etPassword.text.toString().trim()
                    )
                binding.tvRegister -> {
                    vm.registerWanAndroid(
                        binding.etPhone.text.toString().trim(),
                        binding.etPassword.text.toString().trim()
                    )
                }
            }
        }
    }

    override fun initData() {
    }

    override fun showLoading() {
        ProgressUtil.show(mContext, "登录中", false)
    }

    override fun hideLoading() {
        ProgressUtil.dismiss(mContext)
    }
}