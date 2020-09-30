package com.cya.application.ft_home.main.home

import com.cya.application.ft_home.api.HomeAPI
import com.cya.frame.base.vm.BaseRepository

class HomeRepo(private val api: HomeAPI) : BaseRepository() {

    suspend fun requestArticleList(pageId: Int) =
        api.getArticleList(pageId)


}