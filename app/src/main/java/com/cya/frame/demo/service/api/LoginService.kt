package com.cya.frame.demo.service.api

import com.cya.frame.demo.bean.HttpBaseResponse
import com.cya.frame.demo.bean.result.LoginResult
import com.cya.frame.demo.bean.result.UserResult
import retrofit2.http.*

interface LoginService {

    companion object {

        const val BASE_URL = "https://uat-fs.dongfeng-click.cn/"

    }

    @FormUrlEncoded
    @POST("api/login/mobile/login")
    suspend fun loginByPhone(@FieldMap map: MutableMap<String, Any?>): HttpBaseResponse<LoginResult>

    @POST("api/customer/getCustomerInfo")
    suspend fun getUserInfo(@Body map: MutableMap<String, Any?>): HttpBaseResponse<UserResult>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun loginWanAndroid(@FieldMap map: MutableMap<String, Any?>): HttpBaseResponse<LoginResult>

    @FormUrlEncoded
    @POST("user/register")
    suspend fun registerWanAndroid(@FieldMap map: MutableMap<String, Any?>): HttpBaseResponse<LoginResult>
}