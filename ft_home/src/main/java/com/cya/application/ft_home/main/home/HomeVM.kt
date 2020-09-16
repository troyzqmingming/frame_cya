package com.cya.application.ft_home.main.home

import androidx.lifecycle.MutableLiveData
import com.cya.frame.base.Results
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.lib_base.base.CyaBaseVM
import com.cya.lib_base.entity.ArticleListResult

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
            checkResult(repository.requestArticleList(curArticlePage), {
                articleListLiveData.postValue(it)
            })
        }
    }
}