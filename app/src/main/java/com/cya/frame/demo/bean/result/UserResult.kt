package com.cya.frame.demo.bean.result

import com.google.gson.annotations.SerializedName

data class UserResult(
    @SerializedName("nick_name", alternate = ["nickname"]) var nickname: String
)