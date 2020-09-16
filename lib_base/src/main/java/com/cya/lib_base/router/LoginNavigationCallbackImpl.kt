package com.cya.lib_base.router

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.cya.lib_base.contract.ConstantsPath
import com.cya.lib_base.ext.router
import com.orhanobut.logger.Logger

class LoginNavigationCallbackImpl : NavigationCallback {
    override fun onFound(postcard: Postcard?) {
    }

    override fun onLost(postcard: Postcard?) {
    }

    override fun onArrival(postcard: Postcard?) {
    }

    override fun onInterrupt(postcard: Postcard) {
        val path = postcard.path
        Logger.e("拦截到了:$path")
//        router(ConstantsPath.UI.LOGIN)
        //登录成功，跳转被拦截页面
        ARouter.getInstance().build(ConstantsPath.UI.LOGIN)
            .with(postcard.extras)
            .withString("nextPath", path)
            .navigation();
    }

}