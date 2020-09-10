package com.cya.application.api

import com.cya.application.bean.ArticleResult
import com.cya.library_base.bean.response.HttpBaseResponse
import com.cya.library_base.constant.url.BaseUrl
import com.cya.library_base.net.retorfit.RetrofitClient
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeAPI {

    @GET("article/list/{pageId}/json")
    suspend fun getArticleList(@Path("pageId") pageId: Int): HttpBaseResponse<ArticleResult>
}

object HomeService :
    HomeAPI by RetrofitClient.instance.getService(
        HomeAPI::class.java,
        BaseUrl.URL_WAN_ANDROID
    )