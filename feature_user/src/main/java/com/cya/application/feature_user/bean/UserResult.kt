package com.cya.application.feature_user.bean

import com.google.gson.annotations.SerializedName

data class UserResult(
    @SerializedName("nickname") var nickname: String
)