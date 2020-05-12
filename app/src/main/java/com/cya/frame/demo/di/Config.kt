package com.cya.frame.demo.di

import com.cya.frame.data.Preference
import com.cya.frame.demo.data.Contract

object Config {

    object Account {

        var isLogin: Boolean by Preference(Contract.PreferenceKey.USER_IS_LOGIN, false)

        var userInfoData: String by Preference(Contract.PreferenceKey.USER_INFO, "")
    }
}