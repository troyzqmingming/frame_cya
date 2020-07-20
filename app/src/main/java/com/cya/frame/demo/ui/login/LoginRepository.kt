package com.cya.frame.demo.ui.login

import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.di.Config
import com.cya.frame.demo.ext.clearUserCache
import com.cya.frame.demo.ext.saveUserCache
import com.cya.frame.demo.service.LoginService
import com.cya.frame.demo.service.api.LoginAPI
import com.cya.frame.retrofit.BaseResult
import com.google.gson.Gson

class LoginRepository(private val loginApi: LoginAPI) : DemoBaseRepository() {

    fun logoutWanAndroid(action: (() -> Unit)? = null) {
        Config.Account.clearUserCache()
        action?.invoke()
    }

    suspend fun requestRegisterWanAndroid(
        username: String,
        password: String
    ): BaseResult<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        mutableMap["repassword"] = password
        val response = loginApi.registerWanAndroid(mutableMap)
        return executeResponse(response, {
            Config.Account.saveUserCache(it)
        })
    }

    suspend fun requestLoginWanAndroid(
        username: String,
        password: String
    ): BaseResult<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        val response = loginApi.loginWanAndroid(mutableMap)
        return executeResponse(response, {
            Config.Account.saveUserCache(it)
        })
    }
}