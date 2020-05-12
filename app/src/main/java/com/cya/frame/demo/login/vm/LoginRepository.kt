package com.cya.frame.demo.login.vm

import com.cya.frame.data.Preference
import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.di.Config
import com.cya.frame.demo.service.RetrofitClient
import com.cya.frame.demo.service.api.BaseUrl
import com.cya.frame.demo.service.api.LoginService
import com.cya.frame.retrofit.BaseResult
import com.google.gson.Gson

class LoginRepository : DemoBaseRepository() {

    private val mAPI =
        RetrofitClient().getService(LoginService::class.java, BaseUrl.URL_WAN_ANDROID)

    fun logoutWanAndroid(action: (() -> Unit)? = null) {
        Config.Account.isLogin = false
        Config.Account.userInfoData = ""
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
        val response = mAPI.registerWanAndroid(mutableMap)
        return executeResponse(response, {
            Config.Account.isLogin = true
            Config.Account.userInfoData = Gson().toJson(it)
        })
    }

    suspend fun requestLoginWanAndroid(
        username: String,
        password: String
    ): BaseResult<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        val response = mAPI.loginWanAndroid(mutableMap)
        return executeResponse(response, {
            Config.Account.isLogin = true
            Config.Account.userInfoData = Gson().toJson(it)
        })
    }
}