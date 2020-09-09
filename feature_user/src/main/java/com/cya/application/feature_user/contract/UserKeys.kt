package com.cya.application.feature_user.contract

object UserKeys {

    object PreferenceKey {
        /**
         * 是否登录
         */
        const val USER_IS_LOGIN = "user_is_login"

        /**
         * 用户信息缓存
         */
        const val USER_INFO = "user_info"
    }

    object EventKey {

        /**
         * 更新用户信息
         */
        const val UPDATE_INFO = "update_info"

        /**
         * 退出登录
         */
        const val LOGOUT = "logout"
    }
}