package com.cya.frame.demo.bean.result

import com.google.gson.annotations.SerializedName

data class UserResult(
    @SerializedName("nickname") var nickname: String
)