package com.cya.frame.demo.login

import androidx.lifecycle.Observer
import com.cya.frame.demo.base.DemoMVVMActivity
import com.cya.frame.demo.base.Resource
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.databinding.ActivityLoginBinding
import com.cya.frame.demo.login.vm.LoginViewModel
import com.cya.frame.ext.toast
import org.koin.android.viewmodel.ext.android.getViewModel

class LoginActivity : DemoMVVMActivity<ActivityLoginBinding, LoginViewModel>() {


    override fun initViewModel(): LoginViewModel {
        return getViewModel()
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun showLoading() {
        super.showLoading()
        showCommonProgress()
    }

    override fun hideLoading() {
        super.hideLoading()
        dismissCommonProgress()
    }

    override fun startObserve() {
        super.startObserve()
        vm.apply {
            userState.observe(
                this@LoginActivity,
                Observer<Resource<UserResult>> {
                    it.data?.let { user ->
                        binding.tvMsg.text = user.nickname
                        finish()
                    }
                    it.msg?.let { error ->
                        toast(error)
                    }
                })
        }
    }

    override fun initView() {
        binding.btnLogin.setOnClickListener {
            vm.loginWanAndroid(
                binding.etPhone.text.toString().trim(),
                binding.etCode.text.toString().trim()
            )
        }
        binding.btnRegister.setOnClickListener {
            vm.registerWanAndroid(
                binding.etPhone.text.toString().trim(),
                binding.etCode.text.toString().trim()
            )
        }
    }

    override fun initData() {
    }

}