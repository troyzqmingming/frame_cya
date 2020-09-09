package com.cya.application.feature_user.result

import com.google.gson.annotations.SerializedName

data class UserResult(
    @SerializedName("nickname") var nickname: String
)