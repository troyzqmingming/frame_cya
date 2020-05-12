package com.cya.frame.demo.personal

import androidx.lifecycle.Observer
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.FragmentPersonalBinding
import com.cya.frame.demo.di.getUserInfo
import com.cya.frame.demo.di.isLogin
import com.cya.frame.demo.login.LoginActivity
import com.cya.frame.demo.personal.vm.PersonalViewModel
import com.cya.frame.ext.*
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.android.viewmodel.ext.android.getViewModel

class PersonalFragment : BaseMVVMFragment<FragmentPersonalBinding, PersonalViewModel>() {
    override fun getViewBinding(): FragmentPersonalBinding {
        return FragmentPersonalBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.btnLogin.setOnClickListener {
            startActivity(LoginActivity::class.java)
        }
        binding.btnLogout.setOnClickListener {
            vm.logoutUser()
        }
    }

    override fun initData() {
        setUserView(isLogin(), getUserInfo())
    }

    override fun initViewModel(): PersonalViewModel {
        return getViewModel()
    }

    override fun startObserve() {
        LiveEventBus
            .get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
            .observe(this, Observer {
                setUserView(true, it)
            })
        LiveEventBus
            .get(Contract.EventKey.User.LOGOUT, Boolean::class.java)
            .observe(this, Observer {
                setUserView(false, null)
            })
    }

    private fun setUserView(isLogin: Boolean = false, userResult: UserResult?) {
        isLogin.yes {
            binding.llPartLogin.gone()
            binding.llPartUserInfo.visible()
            binding.tvUserName.text = userResult?.nickname
        }.otherwise {
            binding.llPartLogin.visible()
            binding.llPartUserInfo.gone()
        }
    }
}