package com.cya.frame.demo.personal

import android.view.View
import androidx.lifecycle.Observer
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.FragmentPersonalBinding
import com.cya.frame.demo.login.LoginActivity
import com.cya.frame.demo.personal.vm.PersonalViewModel
import com.cya.frame.ext.startActivity
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
    }

    override fun initData() {
    }

    override fun initViewModel(): PersonalViewModel {
        return getViewModel()
    }

    override fun startObserve() {
        vm.uiState.observe(this, Observer {
            if (it.isLogin) {
                binding.llPartLogin.visibility = View.GONE
                binding.llPartUserInfo.visibility = View.VISIBLE
                binding.tvUserName.text = it.userResult?.nickname
            } else {
                binding.llPartLogin.visibility = View.VISIBLE
                binding.llPartUserInfo.visibility = View.GONE
            }

        })
        LiveEventBus
            .get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
            .observe(this, Observer {
                binding.llPartLogin.visibility = View.GONE
                binding.llPartUserInfo.visibility = View.VISIBLE
                it?.let {
                    binding.tvUserName.text = it.nickname
                }

            })
    }
}