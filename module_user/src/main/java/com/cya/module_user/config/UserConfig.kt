package com.cya.module_user.config

import com.cya.frame.data.Preference
import com.cya.module_user.result.UserResult
import com.cya.module_user.data.UserContract
import com.google.gson.Gson

/**
  *
  * @Package:        com.cya.module_user.config
  * @Description:
  * @Author:         CYA
  * @CreateDate:     2020/9/8 9:49 AM
 */
object UserConfig {

    var isLogin: Boolean by Preference(UserContract.PreferenceKey.USER_IS_LOGIN, false)

    var userInfoData: String by Preference(UserContract.PreferenceKey.USER_INFO, "")
}

 /**
  * @method
  * @description 清空用户信息
  * @author: CYA
  * @param
  * @CreateDate:     2020/9/8 9:48 AM
  * @return
  */
fun UserConfig.clearUserCache() {
    isLogin = false
    userInfoData = ""
}
 /**
  * @method
  * @description 保存用户信息
  * @author: CYA
  * @param
  * @CreateDate:     2020/9/8 9:48 AM
  * @return
  */
fun UserConfig.saveUserCache(userResult: UserResult?) {
    isLogin = true
    userInfoData = Gson().toJson(userResult)
}

 /**
  * @method
  * @description 获取用户信息
  * @author: CYA
  * @param
  * @CreateDate:     2020/9/8 9:49 AM
  * @return
  */
fun UserConfig.getUserCache(): UserResult? {
    return Gson().fromJson(userInfoData, UserResult::class.java) ?: null
}