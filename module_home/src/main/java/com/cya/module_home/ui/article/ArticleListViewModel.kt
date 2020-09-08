package com.cya.module_home.ui.article


import androidx.lifecycle.MutableLiveData
import com.cya.module_home.result.ArticleResult
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.lib_base.vm.CYABaseViewModel

class ArticleListViewModel(repository: ArticleListRepository) :
    CYABaseViewModel<ArticleListRepository>(repository) {

    val articleListLiveData = MutableLiveData<ArticleResult>()

    var curPageId = 0

    fun loadArticle(isRefresh: Boolean = true) = getArticleList(isRefresh)

    private fun getArticleList(isRefresh: Boolean) {
        (isRefresh).yes {
            curPageId = 0
            curPageId
        }.otherwise {
            ++curPageId
        }
        launch({
            repository.requestArticleList(curPageId)
        }, success = {
            articleListLiveData.postValue(it)
        })

    }

}