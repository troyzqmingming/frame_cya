package com.cya.ft_user.login

import com.cya.frame.base.vm.BaseRepository
import com.cya.ft_user.api.UserAPI
import com.cya.lib_base.entity.HttpBaseResponse
import com.cya.lib_base.entity.UserResult

class LoginRepo(private val api: UserAPI) : BaseRepository() {


    fun logoutWanAndroid(action: (() -> Unit)? = null) {
        action?.invoke()
    }

    suspend fun requestRegisterWanAndroid(
        username: String,
        password: String
    ): HttpBaseResponse<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>().apply {
            put("username", username)
            put("password", password)
            put("repassword", password)
        }
        return api.registerWanAndroid(mutableMap)
    }

    suspend fun requestLoginWanAndroid(
        username: String,
        password: String
    ): HttpBaseResponse<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>().apply {
            put("username", username)
            put("password", password)
        }
        return api.loginWanAndroid(mutableMap)
    }
}