package com.cya.frame.demo.login.vm

import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.demo.bean.result.LoginResult
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.data.Preference
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.service.api.LoginService
import com.cya.frame.demo.service.RetrofitClient
import com.cya.frame.demo.service.api.BaseUrl
import com.cya.frame.retrofit.BaseResult
import com.google.gson.Gson

class LoginRepository : DemoBaseRepository() {

    var isLogin: Boolean by Preference(Contract.PreferenceKey.USER_IS_LOGIN, false)

    var userInfoData: String by Preference(Contract.PreferenceKey.USER_INFO, "")

    suspend fun loginWanAndroid(username: String, password: String): BaseResult<LoginResult> {
        return safeCall({
            requestLoginWanAndroid(username, password)
        }, {
            it
        })
    }
    suspend fun registerWanAndroid(username: String, password: String): BaseResult<LoginResult> {
        return safeCall({
            requestRegisterWanAndroid(username, password)
        }, {
            it
        })
    }

    suspend fun login(phone: String, code: String): BaseResult<LoginResult> {
        return safeCall(
            {
                requestLogin(phone, code)
            }, {
                it
            }, "登陆失败,请稍后重试"
        )


    }

    suspend fun loginAndGetUserInfo(phone: String, code: String): BaseResult<UserResult> {
        return safeCall(
            {
                when (val result = requestLogin(phone, code)) {
                    //登陆成功
                    is BaseResult.Success -> {
                        val userResult = requestGetUserInfo(result.data?.token)
                        //保存用户信息
                        if (userResult is BaseResult.Success) {
                            isLogin = true
                            userInfoData = Gson().toJson(userResult.data)
                        }
                        userResult
                    }
                    //登陆失败
                    is BaseResult.Failed -> {
                        BaseResult.Failed(result.exception, result.errorMsg)
                    }
                }
            }, {
                //todo 处理异常
                it
            }
        )
    }

    private suspend fun requestLogin(phone: String, code: String): BaseResult<LoginResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["mobile"] = phone
        mutableMap["captcha"] = code
        mutableMap["event"] = "login"
        mutableMap["sourcekey"] = "257ef09a04c62a0fab39ea89267cf525"
        val response =
            RetrofitClient()
                .getService(LoginService::class.java, LoginService.BASE_URL)
                .loginByPhone(mutableMap)
        return convertResponse(response)
    }

    private suspend fun requestGetUserInfo(token: String?): BaseResult<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["token"] = token
        mutableMap["sourcekey"] = "257ef09a04c62a0fab39ea89267cf525"
        val response =
            RetrofitClient()
                .getService(LoginService::class.java, LoginService.BASE_URL)
                .getUserInfo(mutableMap)
        return convertResponse(response)
    }


    private suspend fun requestRegisterWanAndroid(
        username: String,
        password: String
    ): BaseResult<LoginResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        mutableMap["repassword"] = password
        val response =
            RetrofitClient()
                .getService(LoginService::class.java, BaseUrl.URL_WAN_ANDROID)
                .registerWanAndroid(mutableMap)
        return convertResponse(response)
    }

    private suspend fun requestLoginWanAndroid(
        username: String,
        password: String
    ): BaseResult<LoginResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        val response =
            RetrofitClient()
                .getService(LoginService::class.java, BaseUrl.URL_WAN_ANDROID)
                .loginWanAndroid(mutableMap)
        return convertResponse(response)
    }
}