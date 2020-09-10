package com.cya.application.bean

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("link")
    var link: String,
    @SerializedName("title")
    var title: String,
    var bgColor: Int
)