package com.cya.frame.demo.ui.login

import androidx.lifecycle.Observer
import com.cya.frame.demo.base.DemoBaseMVVMFragment
import com.cya.frame.demo.databinding.FragmentLoginBinding
import com.cya.frame.demo.ext.finish
import com.cya.frame.demo.view.ProgressUtil
import com.cya.frame.ext.setNoRepeatClick
import com.cya.frame.ext.toast
import org.koin.android.viewmodel.ext.android.getViewModel

class LoginFragment : DemoBaseMVVMFragment<FragmentLoginBinding, LoginViewModel>() {


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

    override fun showLoading() {
        ProgressUtil.show(mActivity, "登录中")
    }

    override fun hideLoading() {
        ProgressUtil.dismiss(mActivity)
    }

    override fun initView() {
        super.initView()
        binding.layoutTitle.toolbar.setTitleText("登录")
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

}