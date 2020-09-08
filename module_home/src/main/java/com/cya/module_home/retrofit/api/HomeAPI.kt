package com.cya.module_home.retrofit.api

import com.cya.module_home.result.ArticleResult
import com.cya.lib_base.bean.HttpBaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeAPI {


    interface Article {
        @GET("article/list/{pageId}/json")
        suspend fun getArticleList(@Path("pageId") pageId: Int): HttpBaseResponse<ArticleResult>
    }
}