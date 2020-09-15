package com.cya.lib_base.entity

import com.google.gson.annotations.SerializedName

data class ArticleListResult(
    @SerializedName("pageCount") var pageCount: Int,
    @SerializedName("datas") var list: MutableList<Article>
)

data class Article(
    @SerializedName("link")
    var link: String,
    @SerializedName("title")
    var title: String,
    var bgColor: Int
)