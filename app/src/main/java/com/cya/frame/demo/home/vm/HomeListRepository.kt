package com.cya.frame.demo.home.vm

import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.demo.bean.result.ArticleResult
import com.cya.frame.demo.service.RetrofitClient
import com.cya.frame.demo.service.api.BaseUrl
import com.cya.frame.demo.service.api.HomeService
import com.cya.frame.retrofit.BaseResult

class HomeListRepository : DemoBaseRepository() {

    lateinit var articleData: ArticleResult

    suspend fun getArticleList(pageId: Int): BaseResult<ArticleResult> {
        return safeCall({
            requestArticleList(pageId)
        }, {
            it
        }, "获取失败，请稍后重试")
    }


    private suspend fun requestArticleList(pageId: Int) =
        convertResponse(
            RetrofitClient().getService(HomeService.Article::class.java, BaseUrl.URL_WAN_ANDROID)
                .getArticleList(pageId)
        )
}
