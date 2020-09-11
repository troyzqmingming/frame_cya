package com.cya.wan_android.ui.main.home

import com.cya.frame.base.vm.BaseRepository
import com.cya.wan_android.api.HomeAPI

class HomeRepo(private val api: HomeAPI) : BaseRepository() {

    suspend fun requestArticleList(pageId: Int) = api.getArticleList(pageId).build()

}