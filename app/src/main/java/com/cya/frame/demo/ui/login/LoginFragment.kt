package com.cya.frame.demo.ui.login

import androidx.lifecycle.Observer
import com.cya.frame.demo.base.DemoBaseMVVMFragment
import com.cya.frame.demo.databinding.FragmentLoginBinding
import com.cya.frame.demo.ext.finish
import com.cya.frame.demo.view.ProgressUtil
import com.cya.frame.ext.toast
import com.orhanobut.logger.Logger
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
        Logger.e("显示loading")
        ProgressUtil.show(mActivity, "登录中")
    }

    override fun hideLoading() {
        Logger.e("隐藏loading")
        ProgressUtil.dismiss(mActivity)
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