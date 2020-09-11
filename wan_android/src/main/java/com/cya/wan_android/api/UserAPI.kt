package com.cya.wan_android.api

import com.cya.wan_android.contract.HttpUrl
import com.cya.wan_android.entity.HttpBaseResponse
import com.cya.wan_android.entity.UserResult
import com.cya.wan_android.net.RetrofitClient
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun loginWanAndroid(@FieldMap map: MutableMap<String, Any?>): Response<HttpBaseResponse<UserResult>>

    @FormUrlEncoded
    @POST("user/register")
    suspend fun registerWanAndroid(@FieldMap map: MutableMap<String, Any?>): Response<HttpBaseResponse<UserResult>>
}

object UserService :
    UserAPI by RetrofitClient.instance.getService(
        UserAPI::class.java,
        HttpUrl.BASE_URL
    )