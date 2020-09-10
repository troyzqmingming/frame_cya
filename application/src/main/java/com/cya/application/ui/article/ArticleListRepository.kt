package com.cya.application.ui.article

import com.cya.application.api.HomeAPI
import com.cya.application.bean.ArticleResult
import com.cya.library_base.vm.CYABaseRepository


class ArticleListRepository(private val articleApi: HomeAPI) : CYABaseRepository() {

    suspend fun requestArticleList(
        pageId: Int
    ): ArticleResult? {
        return articleApi.getArticleList(pageId).build()
    }
}
