package com.cya.frame.demo.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.demo.bean.result.Article
import com.cya.frame.demo.databinding.FragmentHomeListBinding
import kotlinx.coroutines.launch

class HomeListViewModel(repository: HomeListRepository) :
    BaseViewModel<FragmentHomeListBinding, HomeListRepository>(repository) {

    var uiState = MutableLiveData<UIState>()

    var curPageId = 0

    fun refreshArticle() = getArticleList(0)
    fun loadMoreArticle() = getArticleList(++curPageId)

    private fun getArticleList(pageId: Int) {
        viewModelScope.launch {
            val result = repository.getArticleList(pageId)
            this@HomeListViewModel.checkResult(result, {
                it?.let {
                    repository.articleData = it
                }
                emitUIState(it?.list, true, pageId == 0, pageId > 0)
            }, { i, j ->
                emitUIState(
                    isRefreshArticle = pageId == 0,
                    isLoadMoreArticle = pageId > 0,
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