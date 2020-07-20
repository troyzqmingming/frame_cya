package com.cya.frame.demo.ui.article

import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.demo.service.api.HomeAPI

class ArticleListRepository(private val articleApi: HomeAPI.Article) : DemoBaseRepository() {

    suspend fun requestArticleList(pageId: Int) =
        executeResponse(articleApi.getArticleList(pageId))
}
