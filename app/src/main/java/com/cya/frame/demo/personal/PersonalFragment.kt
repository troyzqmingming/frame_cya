package com.cya.frame.demo.personal

import androidx.lifecycle.Observer
import com.cya.frame.base.holder.Loading
import com.cya.frame.base.holder.State
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.FragmentPersonalBinding
import com.cya.frame.demo.di.getUserInfo
import com.cya.frame.demo.di.isLogin
import com.cya.frame.demo.login.LoginActivity
import com.cya.frame.demo.personal.vm.PersonalViewModel
import com.cya.frame.demo.view.Progress
import com.cya.frame.demo.view.dismiss
import com.cya.frame.demo.view.show
import com.cya.frame.ext.*
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.android.viewmodel.ext.android.getViewModel

class PersonalFragment : BaseMVVMFragment<FragmentPersonalBinding, PersonalViewModel>() {

    private var progress: Progress? = null

    override fun getViewBinding(): FragmentPersonalBinding {
        return FragmentPersonalBinding.inflate(layoutInflater)
    }

    override fun initView() {
        activity?.let {
            progress = Progress(it).setMsg("加载中").isCancelable(false)
        }
        binding.btnLogin.setOnClickListener {
            startActivity(LoginActivity::class.java)
        }
        binding.btnLogout.setOnClickListener {
            vm.logoutUser()
        }
        binding.btnDownload.setOnClickListener {
            vm.downloadFile("https://www.nikon.com.cn/manual/D7000.pdf")
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
        vm.getObservable(Loading::class.java).observe(viewLifecycleOwner, Observer {
            when (it.state) {
                State.LOADING_SHOW -> {
                    activity?.let { act ->
                        progress?.setMsg(it.msg)
                        progress?.show(act)
                    }
                }
                State.LOADING_HIDE -> {
                    activity?.let { act ->
                        progress?.dismiss(act)
                    }
                }
            }

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