package com.cya.frame.demo.ext

import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.config.Config
import com.google.gson.Gson

fun Config.Account.clearUserCache() {
    isLogin = false
    userInfoData = ""
}

fun Config.Account.saveUserCache(userResult: UserResult?) {
    isLogin = true
    userInfoData = Gson().toJson(userResult)
}

fun Config.Account.getUserCache(): UserResult? {
    return Gson().fromJson(userInfoData, UserResult::class.java) ?: null
}