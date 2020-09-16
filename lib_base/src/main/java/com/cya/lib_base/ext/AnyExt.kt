package com.cya.lib_base.ext

import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.cya.frame.CyaSDK
import com.cya.lib_base.router.LoginNavigationCallbackImpl

/**
 * 路由
 */
fun Any.router(path: String) {
    ARouter.getInstance().build(path).navigation()
}

fun Any.routerIntercept(path: String, callback: NavigationCallback = LoginNavigationCallbackImpl()) {
    ARouter.getInstance().build(path).navigation(CyaSDK.getContext(), callback)
}