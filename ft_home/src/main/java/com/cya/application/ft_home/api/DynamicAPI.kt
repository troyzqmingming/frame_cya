package com.cya.application.ft_home.api

import com.cya.application.ft_home.main.dynamic.entity.DynamicResult
import com.cya.lib_base.contract.HttpUrl
import com.cya.lib_base.entity.HttpBaseResponse
import com.cya.lib_base.net.RetrofitClient
import retrofit2.http.GET
import retrofit2.http.Path

interface DynamicAPI {

    @GET("article/list/{pageId}/json")
    suspend fun getDynamicList(@Path("pageId") pageId: Int): HttpBaseResponse<DynamicResult>
}

object DynamicService :
    DynamicAPI by RetrofitClient.instance.getService(
        DynamicAPI::class.java,
        HttpUrl.BASE_URL
    )