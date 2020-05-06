package com.cya.frame.demo.bean.result

import com.google.gson.annotations.SerializedName

/**
 * 登陆返回
 */
data class LoginResult(
    @SerializedName("token") var token: String,
    @SerializedName("nickname") var nickname: String
)