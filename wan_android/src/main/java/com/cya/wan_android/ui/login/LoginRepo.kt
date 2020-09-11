package com.cya.wan_android.ui.login

import com.cya.frame.base.Results
import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.ext.progressApiResponse
import com.cya.wan_android.api.UserAPI
import com.cya.wan_android.entity.HttpBaseResponse
import com.cya.wan_android.entity.UserResult

class LoginRepo(private val api: UserAPI) : BaseRepository() {


    fun logoutWanAndroid(action: (() -> Unit)? = null) {
//        Config.Account.clearUserCache()
        action?.invoke()
    }

    suspend fun requestRegisterWanAndroid(
        username: String,
        password: String
    ) = progressApiResponse {
        val mutableMap = mutableMapOf<String, Any?>().apply {
            put("username", username)
            put("password", password)
            put("repassword", password)
        }
        api.registerWanAndroid(mutableMap)
    }

    suspend fun requestLoginWanAndroid(
        username: String,
        password: String
    ) = progressApiResponse {
        val mutableMap = mutableMapOf<String, Any?>().apply {
            put("username", username)
            put("password", password)
        }
        api.loginWanAndroid(mutableMap)
    }
}