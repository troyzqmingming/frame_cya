package com.cya.ft_user.service

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cya.ft_user.login.LoginActivity
import com.cya.lib_base.contract.ConstantsPath
import com.cya.lib_base.entity.UserResult
import com.cya.lib_base.service.user.LoginService

/**
 * 实现服务接口
 *
 * [com.cya.lib_base.service.user.wrap.LoginServiceImplWrap]
 */
@Route(path = ConstantsPath.USER_LOGIN_PATH)
class LoginServiceImpl : LoginService {

    override fun isLogin(): Boolean {
        return com.cya.ft_user.manager.isLogin
    }

    override fun getUserCache(): UserResult? {
        return com.cya.ft_user.manager.getUserCache()
    }

    override fun clearUserCache() {
        return com.cya.ft_user.manager.clearUserCache()
    }

    override fun login(context: Context) {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }


    override fun init(context: Context?) {
    }

}