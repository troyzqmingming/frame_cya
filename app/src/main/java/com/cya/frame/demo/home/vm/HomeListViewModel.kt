package com.cya.frame.demo.home.vm

import androidx.lifecycle.MutableLiveData
import com.cya.frame.demo.base.Resource
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.Article
import com.cya.frame.demo.databinding.FragmentHomeListBinding
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import kotlinx.coroutines.withContext

class HomeListViewModel(repository: HomeListRepository) :
    DemoBaseViewModel<FragmentHomeListBinding, HomeListRepository>(repository) {

    private var _uiState = MutableLiveData<Resource<MutableList<Article>>>()
    val uiState
        get() = _uiState

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
            emitUIState(it?.list)
        }, {
            emitUIState(errorMsg = it)
        })
    }

    private fun emitUIState(
        articleList: MutableList<Article>? = null,
        errorMsg: String? = null
    ) {
        _uiState.value = Resource(articleList, msg = errorMsg)
    }
}