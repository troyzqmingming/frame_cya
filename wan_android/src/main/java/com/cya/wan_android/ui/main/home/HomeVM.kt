package com.cya.wan_android.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.wan_android.bean.home.ArticleListResult

class HomeVM(repo: HomeRepo) : BaseViewModel<HomeRepo>(repo) {

    val articleListLiveData = MutableLiveData<ArticleListResult>()

    var curArticlePage = 0

    fun loadArticle(isRefresh: Boolean = true) = getArticleList(isRefresh)

    private fun getArticleList(isRefresh: Boolean) {
        (isRefresh).yes {
            curArticlePage = 0
            curArticlePage
        }.otherwise {
            ++curArticlePage
        }
        launch({
            repository.requestArticleList(curArticlePage)
        }, success = {
            articleListLiveData.postValue(it)
        })

    }
}