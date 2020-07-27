package com.cya.frame.demo.ui.article

import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.demo.bean.result.ArticleResult
import com.cya.frame.demo.service.api.HomeAPI
import kotlinx.coroutines.CoroutineScope

class ArticleListRepository(private val articleApi: HomeAPI.Article) : DemoBaseRepository() {

    fun requestArticleList(
        pageId: Int,
        successBlock: (ArticleResult?) -> Unit
    ) {
        launch(
            block = {
                articleApi.getArticleList(pageId).build()
            },
            success = {
                successBlock(it)
            }
        )
    }
}
