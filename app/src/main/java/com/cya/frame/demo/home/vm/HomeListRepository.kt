package com.cya.frame.demo.home.vm

import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.demo.service.RetrofitClient
import com.cya.frame.demo.service.api.BaseUrl
import com.cya.frame.demo.service.api.HomeService

class HomeListRepository : DemoBaseRepository() {

    private val mAPI =
        RetrofitClient().getService(HomeService.Article::class.java, BaseUrl.URL_WAN_ANDROID)

    suspend fun requestArticleList(pageId: Int) =
        executeResponse(mAPI.getArticleList(pageId))
}
