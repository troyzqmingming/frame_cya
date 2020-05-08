package com.cya.frame.demo.login.vm

import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.data.Preference
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.service.api.LoginService
import com.cya.frame.demo.service.RetrofitClient
import com.cya.frame.demo.service.api.BaseUrl
import com.cya.frame.retrofit.BaseResult
import com.google.gson.Gson

class LoginRepository : DemoBaseRepository() {

    var isLogin: Boolean by Preference(Contract.PreferenceKey.USER_IS_LOGIN, false)

    var userInfoData: String by Preference(Contract.PreferenceKey.USER_INFO, "")

    fun logoutWanAndroid(action: (() -> Unit)? = null) {
        isLogin = false
        userInfoData = ""
        action?.invoke()
    }

    suspend fun loginWanAndroid(username: String, password: String): BaseResult<UserResult> {
        return safeCall { requestLoginWanAndroid(username, password) }
    }

    suspend fun registerWanAndroid(username: String, password: String): BaseResult<UserResult> {
        return safeCall { requestRegisterWanAndroid(username, password) }
    }

    private suspend fun requestRegisterWanAndroid(
        username: String,
        password: String
    ): BaseResult<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        mutableMap["repassword"] = password
        val response =
            RetrofitClient()
                .getService(LoginService::class.java, BaseUrl.URL_WAN_ANDROID)
                .registerWanAndroid(mutableMap)
        return convertResponse(response, {
            isLogin = true
            userInfoData = Gson().toJson(it)
        })
    }

    private suspend fun requestLoginWanAndroid(
        username: String,
        password: String
    ): BaseResult<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        val response =
            RetrofitClient()
                .getService(LoginService::class.java, BaseUrl.URL_WAN_ANDROID)
                .loginWanAndroid(mutableMap)
        return convertResponse(response, {
            isLogin = true
            userInfoData = Gson().toJson(it)
        })
    }
}