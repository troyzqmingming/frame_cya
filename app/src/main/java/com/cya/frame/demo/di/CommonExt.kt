package com.cya.frame.demo.di

import com.cya.frame.demo.bean.result.UserResult
import com.google.gson.Gson


fun isLogin() = Config.Account.isLogin

fun getUserInfo() = Gson().fromJson(Config.Account.userInfoData, UserResult::class.java) ?: null