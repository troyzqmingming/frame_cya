package com.cya.frame.demo.ui.mine

import androidx.lifecycle.Observer
import com.cya.frame.demo.NavGraphDirections
import com.cya.frame.demo.base.DemoBaseMVVMFragment
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.FragmentMineBinding
import com.cya.frame.demo.config.getUserInfo
import com.cya.frame.demo.config.isLogin
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
        binding.gUserLayout.allClick {
            isLogin().yes {
                toast("您已登录")
            }.otherwise {
                nav(NavGraphDirections.actionGlobalLoginFragment())
            }
        }
        binding.tvLogout.clickNoRepeat {
            vm.logoutUser()
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
    }

    private fun setUserView(isLogin: Boolean = false, userResult: UserResult?) {
        binding.tvUsername.text = isLogin.yes {
            binding.tvLogout.visible()
            userResult?.nickname
        }.otherwise {
            binding.tvLogout.gone()
            "请登录"
        }
    }
}