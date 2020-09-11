package com.cya.wan_android.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.cya.frame.base.Results
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.wan_android.base.CyaBaseVM
import com.cya.wan_android.entity.ArticleListResult

class HomeVM(repo: HomeRepo) : CyaBaseVM<HomeRepo>(repo) {

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
        viewModelLaunch {
            checkResult(repository.requestArticleList(curArticlePage)) {
                articleListLiveData.postValue(it)
            }
        }
    }
}