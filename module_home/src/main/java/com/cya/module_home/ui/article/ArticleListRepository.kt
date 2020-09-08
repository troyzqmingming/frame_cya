package com.cya.module_home.ui.article

import com.cya.module_home.result.ArticleResult
import com.cya.lib_base.vm.CYABaseRepository
import com.cya.module_home.api.HomeAPI

class ArticleListRepository(private val articleApi: HomeAPI.Article) : CYABaseRepository() {

    suspend fun requestArticleList(
        pageId: Int
    ): ArticleResult? {
        return articleApi.getArticleList(pageId).build()
    }
}
