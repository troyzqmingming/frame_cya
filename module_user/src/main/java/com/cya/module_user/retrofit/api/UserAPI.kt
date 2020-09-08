package com.cya.module_user.retrofit.api

import com.cya.lib_base.bean.HttpBaseResponse
import com.cya.module_user.result.UserResult
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