package com.cya.module_user.login

import com.cya.lib_base.vm.CYABaseRepository
import com.cya.module_user.result.UserResult
import com.cya.module_user.config.UserConfig
import com.cya.module_user.config.clearUserCache
import com.cya.module_user.retrofit.api.UserAPI

class LoginRepository(private val userApi: UserAPI) : CYABaseRepository() {

    fun logoutWanAndroid(action: (() -> Unit)? = null) {
        UserConfig.clearUserCache()
        action?.invoke()
    }

    suspend fun requestRegisterWanAndroid(
        username: String,
        password: String
    ): UserResult? {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        mutableMap["repassword"] = password
        return userApi.registerWanAndroid(mutableMap).build()
    }

    suspend fun requestLoginWanAndroid(
        username: String,
        password: String
    ): UserResult? {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        return userApi.loginWanAndroid(mutableMap).build()
    }
}