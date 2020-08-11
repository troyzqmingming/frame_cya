package com.cya.frame.demo.ui.article


import androidx.lifecycle.MutableLiveData
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.ArticleResult
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes

class ArticleListViewModel(repository: ArticleListRepository) :
    DemoBaseViewModel<ArticleListRepository>(repository) {

    val articleListLiveData = MutableLiveData<ArticleResult>()

    var curPageId = 0

    fun loadArticle(isRefresh: Boolean = true) = getArticleList(isRefresh)

    private fun getArticleList(isRefresh: Boolean) {
        launch({
            repository.requestArticleList(curPageId)
        }, success = {
            (isRefresh).yes {
                curPageId = 0
                curPageId
            }.otherwise {
                ++curPageId
            }
            articleListLiveData.postValue(it)
        })

    }

}