package com.cya.ft_user.login

import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.ext.progressApiResponse
import com.cya.ft_user.api.UserAPI

class LoginRepo(private val api: UserAPI) : BaseRepository() {


    fun logoutWanAndroid(action: (() -> Unit)? = null) {
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