package com.cya.wan_android.ui.main.mine

import com.cya.frame.ext.*
import com.cya.wan_android.NavMainDirections
import com.cya.wan_android.R
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
        binding.toolbar.setOnMenuItemClickListener {
            (it.itemId == R.id.menu_logout).yes {
                vm.logout()
            }
            return@setOnMenuItemClickListener true
        }
    }

    override fun initData() {
        setUser(getUserCache())
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
        binding.toolbar.menu.findItem(R.id.menu_logout).isVisible = isLogin
        isLogin.yes {
            binding.tvUser.text = userResult?.nickname
        }.otherwise {
            binding.tvUser.text = "请登录"
        }
    }
}