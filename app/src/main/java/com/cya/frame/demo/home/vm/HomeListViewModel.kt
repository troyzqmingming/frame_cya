package com.cya.frame.demo.home.vm

import androidx.lifecycle.MutableLiveData
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.Article
import com.cya.frame.demo.databinding.FragmentHomeListBinding

class HomeListViewModel(repository: HomeListRepository) :
    DemoBaseViewModel<FragmentHomeListBinding, HomeListRepository>(repository) {

    private var _uiState = MutableLiveData<UIState>()
    val uiState: MutableLiveData<UIState>
        get() = _uiState

    var curPageId = 0

    fun loadArticle(isRefresh: Boolean = true) = getArticleList(isRefresh)

    private fun getArticleList(isRefresh: Boolean) {
        launchResult({
            repository.getArticleList(
                if (isRefresh) {
                    curPageId = 0
                    curPageId
                } else {
                    ++curPageId
                }
            )
        }, {
            emitUIState(it?.list, true, curPageId == 0, curPageId > 0)
        }, {
            emitUIState(
                isRefreshArticle = curPageId == 0,
                isLoadMoreArticle = curPageId > 0,
                errorMsg = it
            )
        })
    }

    private fun emitUIState(
        articleList: MutableList<Article>? = null,
        isGetDataSuccess: Boolean = false,
        isRefreshArticle: Boolean = false,
        isLoadMoreArticle: Boolean = false,
        errorMsg: String? = null
    ) {
        _uiState.value =
            UIState(articleList, isGetDataSuccess, isRefreshArticle, isLoadMoreArticle, errorMsg)
    }

    data class UIState(
        var articleList: MutableList<Article>?,
        var isGetDataSuccess: Boolean,
        var isRefreshArticle: Boolean,
        var isLoadMoreArticle: Boolean,
        var errorMsg: String?

    )
}