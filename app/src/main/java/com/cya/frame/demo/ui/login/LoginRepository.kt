package com.cya.frame.demo.ui.login

import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.config.Config
import com.cya.frame.demo.ext.clearUserCache
import com.cya.frame.demo.service.api.LoginAPI

class LoginRepository(private val loginApi: LoginAPI) : DemoBaseRepository() {

    fun logoutWanAndroid(action: (() -> Unit)? = null) {
        Config.Account.clearUserCache()
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
        return loginApi.registerWanAndroid(mutableMap).build()
    }

    suspend fun requestLoginWanAndroid(
        username: String,
        password: String
    ): UserResult? {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        return loginApi.loginWanAndroid(mutableMap).build()
    }
}