package com.cya.application.router

import androidx.fragment.app.Fragment
import com.cya.application.MainDirections
import com.cya.application.R
import com.cya.library_base.ext.getNavController
import com.cya.library_base.ext.nav

/**
 *
 * @Package:        com.cya.application.router
 * @Description:    路由管理
 * @Author:         CYA
 * @CreateDate:     2020/9/9 5:26 PM
 */
/**
 * 登录页面
 */
fun Fragment.navLogin() {
    nav(MainDirections.actionGlobalIncludeUser())
}

fun Fragment.navPersonal() {
    nav(MainDirections.actionGlobalPersonalFragment())
}

/**
 * 返回主界面
 */
fun Fragment.backHome(inclusive: Boolean = false) {
    getNavController().popBackStack(R.id.homeFragment, inclusive)
}