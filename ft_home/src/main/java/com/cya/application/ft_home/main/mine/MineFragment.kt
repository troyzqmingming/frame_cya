package com.cya.application.ft_home.main.mine

import android.animation.Animator
import com.alibaba.android.arouter.launcher.ARouter
import com.cya.application.ft_home.R
import com.cya.application.ft_home.databinding.FragmentMineBinding
import com.cya.frame.ext.clickNoRepeat
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.lib_base.base.CyaBaseFragment
import com.cya.lib_base.contract.ConstantsPath
import com.cya.lib_base.contract.EventKey
import com.cya.lib_base.entity.UserResult
import com.cya.lib_base.ext.router
import com.cya.lib_base.ext.routerIntercept
import com.cya.lib_base.router.LoginNavigationCallbackImpl
import com.cya.lib_base.service.user.wrap.LoginServiceImplWrap
import com.jeremyliao.liveeventbus.LiveEventBus
import com.orhanobut.logger.Logger

class MineFragment : CyaBaseFragment<FragmentMineBinding>() {
    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.tvUser.clickNoRepeat {
//            LoginServiceImplWrap.isLogin().yes {
//                router(ConstantsPath.UI.PERSONAL)
//            }.otherwise {
//                LoginServiceImplWrap.login(mActivity)
//            }
            //
            routerIntercept(ConstantsPath.UI.PERSONAL)

        }
//        binding.fabPlay.clickNoRepeat {
//            routerIntercept(ConstantsPath.UI.PLAY)
//        }
//        binding.wifi.clickNoRepeat {
//            binding.wifi.addAnimatorListener(object : Animator.AnimatorListener {
//                override fun onAnimationStart(animation: Animator?) {
//                    Logger.e("播放开始")
//                }
//
//                override fun onAnimationEnd(animation: Animator?) {
//                    Logger.e("播放结束")
//                }
//
//                override fun onAnimationCancel(animation: Animator?) {
//                }
//
//                override fun onAnimationRepeat(animation: Animator?) {
//                }
//
//            })
//            binding.wifi.playAnimation()
//        }
        binding.toolbar.setOnMenuItemClickListener {
            (it.itemId == R.id.menu_logout).yes {
                LoginServiceImplWrap.clearUserCache()
                setUser()
            }
            return@setOnMenuItemClickListener true
        }
    }

    override fun initData() {
        setUser(LoginServiceImplWrap.getUserCache())
    }

    override fun setObserve() {
        LiveEventBus.get(EventKey.UPDATE_INFO, UserResult::class.java).observe(this) {
            setUser(it)
        }
        LiveEventBus.get(EventKey.LOGOUT).observe(this) {
            setUser()
        }
    }

    private fun setUser(userResult: UserResult? = null) {
        binding.toolbar.menu.findItem(R.id.menu_logout).isVisible = LoginServiceImplWrap.isLogin()
        LoginServiceImplWrap.isLogin().yes {
            binding.tvUser.text = userResult?.nickname
        }.otherwise {
            binding.tvUser.text = "请登录"
        }
    }
}