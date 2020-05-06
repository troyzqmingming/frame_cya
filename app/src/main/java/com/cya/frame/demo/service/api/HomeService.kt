package com.cya.frame.demo.service.api

import com.cya.frame.demo.bean.HttpBaseResponse
import com.cya.frame.demo.bean.result.ArticleResult
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {


    interface Article {
        //https://www.wanandroid.com/article/list/0/json
        @GET("article/list/{pageId}/json")
        suspend fun getArticleList(@Path("pageId") pageId: Int): HttpBaseResponse<ArticleResult>
    }
}