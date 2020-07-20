package com.cya.frame.demo.ui.login

import androidx.lifecycle.Observer
import com.cya.frame.base.holder.State
import com.cya.frame.base.holder.UIState
import com.cya.frame.demo.base.DemoBaseMVVMFragment
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.databinding.FragmentLoginBinding
import com.cya.frame.demo.ext.finish
import com.cya.frame.ext.toast
import org.koin.android.viewmodel.ext.android.getViewModel

class LoginFragment : DemoBaseMVVMFragment<FragmentLoginBinding, LoginViewModel>() {


    override fun initViewModel(): LoginViewModel {
        return getViewModel()
    }

    override fun getViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

//    override fun showLoading() {
//        super.showLoading()
//        showCommonProgress()
//    }
//
//    override fun hideLoading() {
//        super.hideLoading()
//        dismissCommonProgress()
//    }

    override fun startObserve() {
//        super.startObserve()
        vm.apply {
            getObservable(UserResult::class.java).observe(
                this@LoginFragment,
                Observer<UIState<UserResult>> {
                    when (it.state) {
                        State.SUCCESS -> {
                            binding.tvMsg.text = it.data?.nickname
                            finish()
                        }
                        State.FAILED -> {
                            it.msg?.let { it1 -> toast(it1) }
                        }
                        else -> {}
                    }
                })
            getObservable(Int::class.java).observe(
                this@LoginFragment,
                Observer {
                    binding.btnTest.text = it.data.toString()
                }
            )
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
        binding.btnTest.setOnClickListener {
            vm.testButton()
        }
    }

    override fun initData() {
    }

}