package com.cya.application.feature_user.api

import com.cya.application.feature_user.result.UserResult
import com.cya.library_base.bean.response.HttpBaseResponse
import com.cya.library_base.constant.url.BaseUrl
import com.cya.library_base.net.retorfit.RetrofitClient
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun loginWanAndroid(@FieldMap map: MutableMap<String, Any?>): HttpBaseResponse<UserResult>

    @FormUrlEncoded
    @POST("user/register")
    suspend fun registerWanAndroid(@FieldMap map: MutableMap<String, Any?>): HttpBaseResponse<UserResult>
}

object UserService :
    UserAPI by RetrofitClient.instance.getService(
        UserAPI::class.java,
        BaseUrl.URL_WAN_ANDROID
    )