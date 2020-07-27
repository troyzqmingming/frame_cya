package com.cya.frame.demo.ui.login

import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.di.Config
import com.cya.frame.demo.ext.clearUserCache
import com.cya.frame.demo.ext.saveUserCache
import com.cya.frame.demo.service.api.LoginAPI
import com.cya.frame.exception.CyaException

class LoginRepository(private val loginApi: LoginAPI) : DemoBaseRepository() {

    fun logoutWanAndroid(action: (() -> Unit)? = null) {
        Config.Account.clearUserCache()
        action?.invoke()
    }

    fun requestRegisterWanAndroid(
        username: String,
        password: String,
        successBlock: (UserResult?) -> Unit,
        error: (CyaException) -> Unit
    ) {
        launch(
            block = {
                val mutableMap = mutableMapOf<String, Any?>()
                mutableMap["username"] = username
                mutableMap["password"] = password
                mutableMap["repassword"] = password
                loginApi.registerWanAndroid(mutableMap).build()
            },
            success = {
                successBlock.invoke(it)
                Config.Account.saveUserCache(it)
            },
            error = {
                error(it)
            }

        )
    }

    fun requestLoginWanAndroid(
        username: String,
        password: String,
        successBlock: (UserResult?) -> Unit,
        error: (CyaException) -> Unit
    ) {
        launch(
            block = {
                val mutableMap = mutableMapOf<String, Any?>()
                mutableMap["username"] = username
                mutableMap["password"] = password
                loginApi.loginWanAndroid(mutableMap).build()
            },
            success = {
                successBlock.invoke(it)
                Config.Account.saveUserCache(it)
            },
            error = {
                error(it)
            })
    }
}