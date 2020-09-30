package com.cya.ft_user.login

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cya.frame.base.vm.HIDE
import com.cya.frame.base.vm.SHOW
import com.cya.frame.ext.clickNoRepeat
import com.cya.frame.ext.toast
import com.cya.ft_user.databinding.ActivityLoginBinding
import com.cya.lib_base.base.CyaBaseVMActivity
import com.cya.lib_base.contract.ConstantsPath
import com.cya.lib_ui.dialog.ProgressUtil
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import org.koin.android.viewmodel.ext.android.getViewModel

@Route(path = ConstantsPath.UI.LOGIN)
class LoginActivity : CyaBaseVMActivity<ActivityLoginBinding, LoginVM>() {

    @Autowired(name = "nextPath")
    @JvmField
    var nexPath: String? = null

    init {
//        ARouter.getInstance().inject(this)
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun initView() {
        Logger.i("拦截到了我的登录:${nexPath}")
        binding.btnLogin.clickNoRepeat {
            vm.login(binding.etPhone.text.toString(), binding.etPassword.text.toString())
        }
        binding.tvRegister.clickNoRepeat {
            vm.register(binding.etPhone.text.toString(), binding.etPassword.text.toString())
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initData() {
    }

    override fun setObserve() {
        vm.apply {
            userLiveData.observe(this@LoginActivity) {
                Logger.e("登录结果:${Gson().toJson(it)}")
                finish()
            }
            loadingLiveData.observe(this@LoginActivity) {
                when (it) {
                    is SHOW -> ProgressUtil.show(this@LoginActivity, "登录中", false)
                    is HIDE -> ProgressUtil.dismiss(this@LoginActivity)
                }
            }
            failedLiveData.observe(this@LoginActivity) {
                toast(it.msg)
            }
            errorLiveData.observe(this@LoginActivity) {
                toast("code:${it.errorCode},msg:${it.errorMsg}")
            }
        }
    }

    override fun initViewModel(): LoginVM {
        return getViewModel()
    }

}