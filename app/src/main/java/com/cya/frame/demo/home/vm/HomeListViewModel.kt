package com.cya.frame.demo.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.Article
import com.cya.frame.demo.databinding.FragmentHomeListBinding
import kotlinx.coroutines.launch

class HomeListViewModel(repository: HomeListRepository) :
    DemoBaseViewModel<FragmentHomeListBinding, HomeListRepository>(repository) {

    var uiState = MutableLiveData<UIState>()

    var curPageId = 0

    fun loadArticle(isRefresh: Boolean = true) = getArticleList(isRefresh)

    private fun getArticleList(isRefresh: Boolean) {
        viewModelScope.launch {
            val result = repository.getArticleList(
                if (isRefresh) {
                    curPageId = 0
                    curPageId
                } else {
                    ++curPageId
                }
            )
            checkResult(result, {
                it?.let {
                    repository.articleData = it
                }
                emitUIState(it?.list, true, curPageId == 0, curPageId > 0)
            }, { i, j ->
                emitUIState(
                    isRefreshArticle = curPageId == 0,
                    isLoadMoreArticle = curPageId > 0,
                    errorMsg = j
                )
            })
        }
    }

    private fun emitUIState(
        articleList: MutableList<Article>? = null,
        isGetDataSuccess: Boolean = false,
        isRefreshArticle: Boolean = false,
        isLoadMoreArticle: Boolean = false,
        errorMsg: String? = null
    ) {
        uiState.value =
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