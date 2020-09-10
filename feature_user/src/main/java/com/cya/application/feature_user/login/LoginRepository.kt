package com.cya.application.feature_user.login

import com.cya.application.feature_user.bean.UserResult
import com.cya.application.feature_user.config.clearUserCache
import com.cya.library_base.vm.CYABaseRepository
import com.cya.application.feature_user.api.UserAPI

class LoginRepository(private val userApi: UserAPI) : CYABaseRepository() {

    fun logoutWanAndroid(action: (() -> Unit)? = null) {
        clearUserCache()
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