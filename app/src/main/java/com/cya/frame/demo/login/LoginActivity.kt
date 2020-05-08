package com.cya.frame.demo.login

import android.app.ProgressDialog
import android.widget.Toast
import androidx.lifecycle.Observer
import com.cya.frame.base.ui.BaseMVVMActivity
import com.cya.frame.demo.databinding.ActivityLoginBinding
import com.cya.frame.demo.login.vm.LoginViewModel
import com.cya.frame.ext.toast
import org.koin.android.viewmodel.ext.android.getViewModel

class LoginActivity : BaseMVVMActivity<ActivityLoginBinding, LoginViewModel>() {


    override fun initViewModel(): LoginViewModel {
        return getViewModel()
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun startObserve() {
        vm.apply {
            //ui update
            mUiState.observe(this@LoginActivity, Observer<LoginViewModel.UIState> {
                if (it.showProgress) {
                    showProgressDialog()
                }
                if (it.hideProgress) {
                    dismissProgressDialog()
                }
                it.userInfo?.let { user ->
                    binding.tvMsg.text = user.nickname
                    finish()
                }
                it.errorMsg?.let { error ->
                    toast(error)
                }
            })
        }
    }

    override fun initView() {
        binding.btnLogin.setOnClickListener {
            vm.loginWanAndroid(binding.etPhone.text.toString(), binding.etCode.text.toString())
        }
        binding.btnRegister.setOnClickListener {
            vm.registerWanAndroid(binding.etPhone.text.toString(), binding.etCode.text.toString())
        }

    }

    override fun initData() {
    }

    private var progressDialog: ProgressDialog? = null
    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }
}