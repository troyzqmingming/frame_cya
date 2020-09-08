package com.cya.module_home.retrofit.service

import com.cya.lib_base.constant.BaseUrl
import com.cya.lib_net.retorfit.RetrofitClient
import com.cya.module_home.retrofit.api.HomeAPI


object HomeArticleService :
    HomeAPI.Article by RetrofitClient.instance.getService(
        HomeAPI.Article::class.java,
        BaseUrl.URL_WAN_ANDROID
    )