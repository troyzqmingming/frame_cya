package com.cya.frame.demo.config

import com.cya.frame.demo.ext.getUserCache


fun isLogin() = Config.Account.isLogin

fun getUserInfo() = Config.Account.getUserCache()