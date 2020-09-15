package com.cya.lib_base.service.user

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider
import com.cya.lib_base.entity.UserResult

/**
 * 依赖注入
 * 通过接口调用服务
 */
interface LoginService : IProvider {

    fun isLogin(): Boolean

    fun getUserCache(): UserResult?

    fun clearUserCache()

    fun login(context: Context)

}