package com.cya.frame.demo.service.api

import com.cya.frame.demo.bean.HttpBaseResponse
import com.cya.frame.demo.bean.result.UserResult
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPI {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun loginWanAndroid(@FieldMap map: MutableMap<String, Any?>): HttpBaseResponse<UserResult>

    @FormUrlEncoded
    @POST("user/register")
    suspend fun registerWanAndroid(@FieldMap map: MutableMap<String, Any?>): HttpBaseResponse<UserResult>
}