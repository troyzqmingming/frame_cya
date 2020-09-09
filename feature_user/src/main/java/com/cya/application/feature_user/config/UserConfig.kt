package com.cya.application.feature_user.config

import com.cya.frame.data.Preference
import com.cya.application.feature_user.result.UserResult
import com.cya.application.feature_user.contract.UserKeys
import com.google.gson.Gson

object UserConfig {

    var isLogin: Boolean by Preference(UserKeys.PreferenceKey.USER_IS_LOGIN, false)

    var userInfoData: String by Preference(UserKeys.PreferenceKey.USER_INFO, "")
}

fun Any.clearUserCache() {
    UserConfig.isLogin = false
    UserConfig.userInfoData = ""
}

fun Any.saveUserCache(userResult: UserResult?) {
    UserConfig.isLogin = true
    UserConfig.userInfoData = Gson().toJson(userResult)
}

fun Any.getUserCache(): UserResult? {
    return Gson().fromJson(UserConfig.userInfoData, UserResult::class.java) ?: null
}