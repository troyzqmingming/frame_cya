package com.cya.wan_android.api

import com.cya.wan_android.bean.HttpBaseResponse
import com.cya.wan_android.bean.home.ArticleListResult
import com.cya.wan_android.contract.HttpUrl
import com.cya.wan_android.net.RetrofitClient
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