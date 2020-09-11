package com.cya.wan_android.ui.main.home

import com.cya.frame.base.Results
import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.ext.progressApiResponse
import com.cya.wan_android.api.HomeAPI
import com.cya.wan_android.entity.ArticleListResult
import com.cya.wan_android.entity.HttpBaseResponse

class HomeRepo(private val api: HomeAPI) : BaseRepository() {

    suspend fun requestArticleList(pageId: Int): Results<HttpBaseResponse<ArticleListResult>> {
        return progressApiResponse {
            api.getArticleList(pageId)
        }
    }

}