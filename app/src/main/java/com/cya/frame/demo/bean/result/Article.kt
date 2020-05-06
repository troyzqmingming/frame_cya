package com.cya.frame.demo.bean.result

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("link")
    var link: String,
    @SerializedName("title")
    var title: String
)