package com.cya.application.ft_home.main.home

import com.cya.application.ft_home.api.HomeAPI
import com.cya.frame.base.Results
import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.ext.progressApiResponse
import com.cya.lib_base.entity.ArticleListResult
import com.cya.lib_base.entity.HttpBaseResponse

class HomeRepo(private val api: HomeAPI) : BaseRepository() {

    suspend fun requestArticleList(pageId: Int): Results<HttpBaseResponse<ArticleListResult>> {
        return progressApiResponse {
            api.getArticleList(pageId)
        }
    }

}