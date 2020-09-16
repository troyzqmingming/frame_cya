package com.cya.application.ft_home.api

import com.cya.lib_base.contract.HttpUrl
import com.cya.lib_base.entity.ArticleListResult
import com.cya.lib_base.entity.HttpBaseResponse
import com.cya.lib_base.net.RetrofitClient
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeAPI {

    @GET("article/list/{pageId}/json")
    suspend fun getArticleList(@Path("pageId") pageId: Int): HttpBaseResponse<ArticleListResult>
}

object HomeService :
    HomeAPI by RetrofitClient.instance.getService(
        HomeAPI::class.java,
        HttpUrl.BASE_URL
    )