package com.cya.module_home.ui.mine

import androidx.lifecycle.Observer
import com.cya.frame.ext.*
import com.cya.lib_base.ext.nav
import com.cya.lib_base.ui.CYABaseMVVMFragment
import com.cya.module_home.databinding.FragmentMineBinding
import com.cya.module_user.config.UserConfig
import com.cya.module_user.config.UserConfig.isLogin
import com.cya.module_user.config.getUserCache
import com.cya.module_user.data.UserContract
import com.cya.module_user.result.UserResult
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.android.viewmodel.ext.android.getViewModel

class MineFragment : CYABaseMVVMFragment<FragmentMineBinding, MineViewModel>() {

    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.gUserLayout.allClick {
//            isLogin.yes {
//                toast("您已登录")
//            }.otherwise {
//                nav(NavGraphDirections.actionGlobalLoginFragment())
//            }
        }
        binding.tvLogout.clickNoRepeat {
            vm.logoutUser()
        }
    }

    override fun initData() {
        setUserView(isLogin, UserConfig.getUserCache())
    }

    override fun initViewModel(): MineViewModel {
        return getViewModel()
    }

    override fun startObserve() {
        LiveEventBus
            .get(UserContract.EventKey.User.UPDATE_INFO, UserResult::class.java)
            .observe(this, Observer {
                setUserView(true, it)
            })
        LiveEventBus
            .get(UserContract.EventKey.User.LOGOUT, Boolean::class.java)
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