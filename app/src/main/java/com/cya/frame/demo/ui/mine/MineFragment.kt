package com.cya.frame.demo.ui.mine

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cya.frame.demo.NavGraphDirections
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseMVVMFragment
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.FragmentMineBinding
import com.cya.frame.demo.di.getUserInfo
import com.cya.frame.demo.di.isLogin
import com.cya.frame.demo.ext.nav
import com.cya.frame.demo.view.Progress
import com.cya.frame.ext.*
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.android.viewmodel.ext.android.getViewModel

class MineFragment : DemoBaseMVVMFragment<FragmentMineBinding, MineViewModel>() {

    private var progress: Progress? = null

    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun initView() {
        activity?.let {
            progress = Progress(it).setMsg("加载中").isCancelable(false)
        }
        setNoRepeatClick(binding.btnLogin, binding.btnLogout, binding.btnDownload) {
            when (it) {
                binding.btnLogin -> nav(NavGraphDirections.actionGlobalLoginFragment())
                binding.btnLogout -> vm.logoutUser()
//                binding.btnDownload -> vm.downloadFile("https://www.nikon.com.cn/manual/D7000.pdf")
            }
        }
    }

    override fun initData() {
        setUserView(isLogin(), getUserInfo())
    }

    override fun initViewModel(): MineViewModel {
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
//        vm.getObservable(Loading::class.java).observe(viewLifecycleOwner, Observer {
//            when (it.state) {
//                State.LOADING_SHOW -> {
//                    activity?.let { act ->
//                        progress?.setMsg(it.msg)
//                        progress?.show(act)
//                    }
//                }
//                State.LOADING_HIDE -> {
//                    activity?.let { act ->
//                        progress?.dismiss(act)
//                    }
//                }
//            }
//
//        })
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