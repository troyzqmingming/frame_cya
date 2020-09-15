package com.cya.lib_base.entity

import com.google.gson.annotations.SerializedName

data class UserResult(
    @SerializedName("nickname") var nickname: String
)