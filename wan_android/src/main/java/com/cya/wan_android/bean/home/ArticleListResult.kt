package com.cya.wan_android.bean.home

import com.google.gson.annotations.SerializedName

data class ArticleListResult(
    @SerializedName("pageCount") var pageCount: Int,
    @SerializedName("datas") var list: MutableList<Article>
)