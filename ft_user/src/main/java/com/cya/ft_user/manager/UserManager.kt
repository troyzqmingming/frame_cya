package com.cya.ft_user.manager

import com.cya.frame.data.Preference
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.lib_base.contract.UserKey
import com.cya.lib_base.entity.UserResult
import com.google.gson.Gson

var isLogin: Boolean by Preference(UserKey.IS_LOGIN, false)

private var userCache: String by Preference(UserKey.INFO_CACHE, "")

fun getUserCache(): UserResult? {
    return isLogin.yes {
        Gson().fromJson(userCache, UserResult::class.java) ?: null
    }.otherwise {
        null
    }
}

fun saveUserCache(userResult: UserResult) {
    isLogin = true
    userCache = Gson().toJson(userResult)
}

fun clearUserCache() {
    isLogin = false
    userCache = ""
}