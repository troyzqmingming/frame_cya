package com.cya.application.ft_home.main.dynamic.entity

import com.google.gson.annotations.SerializedName

data class DynamicResult(
    @SerializedName("pageCount") var pageCount: Int,
    @SerializedName("datas") var list: MutableList<DynamicItem>,
    @SerializedName("curPage") var curPage: Int
) {
    fun isLastPage() = curPage >= pageCount
}

data class DynamicItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    var title: String
)