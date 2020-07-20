package com.cya.frame.demo.ui.article

import com.cya.frame.base.holder.UIState
import com.cya.frame.base.holder.State
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.Article
import com.cya.frame.demo.databinding.FragmentArticleListBinding
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes

class ArticleListViewModel(repository: ArticleListRepository) :
    DemoBaseViewModel<FragmentArticleListBinding, ArticleListRepository>(repository) {


    var curPageId = 0

    fun loadArticle(isRefresh: Boolean = true) = getArticleList(isRefresh)

    private fun getArticleList(isRefresh: Boolean) {
        launchResult({
            repository.requestArticleList(
                (isRefresh).yes {
                    curPageId = 0
                    curPageId
                }.otherwise {
                    ++curPageId
                }
            )
        }, {
            emitUIState(articleList = it?.list)
        }, {
            emitUIState(state = State.FAILED, errorMsg = it)
        })
    }

    private fun emitUIState(
        state: State = State.SUCCESS,
        articleList: MutableList<Article>? = null,
        errorMsg: String? = null
    ) {
        emit2(Article::class.java, UIState(state = state, data = articleList, msg = errorMsg))
    }
}