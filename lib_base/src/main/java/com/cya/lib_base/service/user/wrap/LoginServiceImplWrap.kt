package com.cya.lib_base.service.user.wrap

import android.content.Context
import androidx.lifecycle.LiveData
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.cya.lib_base.contract.ConstantsPath
import com.cya.lib_base.entity.UserResult
import com.cya.lib_base.service.user.LoginService

/**
 * 发现服务
 * 统一调度
 */
object LoginServiceImplWrap {

    @Autowired(name = ConstantsPath.USER_LOGIN_PATH)
    lateinit var service: LoginService

    init {
        ARouter.getInstance().inject(this)
    }

    fun isLogin(): Boolean {
        return service.isLogin()
    }

    fun getUserCache(): UserResult? {
        return service.getUserCache()
    }

    fun clearUserCache() {
        service.clearUserCache()
    }

    fun login(context: Context) {
        return service.login(context)
    }

}