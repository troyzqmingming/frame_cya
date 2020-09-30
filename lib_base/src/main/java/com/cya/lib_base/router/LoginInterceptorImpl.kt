package com.cya.lib_base.router

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.lib_base.contract.ConstantsPath
import com.cya.lib_base.service.user.wrap.LoginServiceImplWrap

/**
 *
 * @Package:        com.cya.lib_base.router
 * @Description:     登录拦截器
 * @Author:         CYA
 * @CreateDate:     2020/9/15 5:16 PM
 */
@Interceptor(name = "login", priority = 6)
class LoginInterceptorImpl : IInterceptor {
    override fun init(context: Context?) {
    }

    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        LoginServiceImplWrap.isLogin().yes {
            callback.onContinue(postcard)
        }.otherwise {
            when (postcard.path) {
                // 不需要拦截
                ConstantsPath.UI.LOGIN -> callback.onContinue(postcard)
                // 拦截
                else -> callback.onInterrupt(null)
            }
        }
    }

}