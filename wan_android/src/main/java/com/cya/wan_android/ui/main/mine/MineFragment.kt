package com.cya.wan_android.ui.main.mine

import com.cya.frame.ext.*
import com.cya.wan_android.NavMainDirections
import com.cya.wan_android.base.CyaBaseFragment
import com.cya.wan_android.base.CyaBaseVMFragment
import com.cya.wan_android.contract.EventKey
import com.cya.wan_android.databinding.FragmentMineBinding
import com.cya.wan_android.entity.UserResult
import com.cya.wan_android.ext.nav
import com.cya.wan_android.manager.getUserCache
import com.cya.wan_android.manager.isLogin
import com.cya.wan_android.ui.login.LoginVM
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.android.viewmodel.ext.android.getViewModel

class MineFragment : CyaBaseVMFragment<FragmentMineBinding, LoginVM>() {
    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.tvUser.clickNoRepeat {
            isLogin.yes {
                nav(NavMainDirections.actionGlobalPersonalFragment())
            }.otherwise {
                nav(NavMainDirections.actionGlobalLoginFragment())
            }
        }
        binding.ivLogout.clickNoRepeat {
            vm.logout()
        }
    }

    override fun initData() {
        isLogin.yes {
            binding.tvUser.text = getUserCache()?.nickname
            binding.ivLogout.visible()
        }
    }

    override fun setObserve() {
        LiveEventBus.get(EventKey.UPDATE_INFO, UserResult::class.java).observe(this) {
            setUser(it)
        }
        LiveEventBus.get(EventKey.LOGOUT).observe(this) {
            setUser()
        }
    }

    override fun initViewModel(): LoginVM {
        return getViewModel()
    }

    private fun setUser(userResult: UserResult? = null) {
        isLogin.yes {
            binding.tvUser.text = userResult?.nickname
            binding.ivLogout.visible()
        }.otherwise {
            binding.tvUser.text = "请登录"
            binding.ivLogout.gone()
        }
    }
}