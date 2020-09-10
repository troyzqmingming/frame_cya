package com.cya.application.bean

import com.google.gson.annotations.SerializedName

data class ArticleResult(
    @SerializedName("pageCount") var pageCount: Int,
    @SerializedName("datas") var list: MutableList<Article>
)