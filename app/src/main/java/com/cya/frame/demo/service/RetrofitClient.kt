package com.cya.frame.demo.service

import com.cya.frame.CyaSDK
import com.cya.frame.demo.service.api.BaseUrl
import com.cya.frame.demo.service.api.HomeAPI
import com.cya.frame.demo.service.api.LoginAPI
import com.cya.frame.retrofit.BaseRetrofitClient
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

class RetrofitClient : BaseRetrofitClient() {

    override fun handleBuilder(builder: OkHttpClient.Builder) {
        builder
            .cache(
                Cache(
                    File(CyaSDK.getContext().externalCacheDir, "test_cache"),
                    10 * 1024 * 1024
                )
            )
            .addInterceptor(CacheInterceptor())
            .addNetworkInterceptor(CacheInterceptor())
    }

}

val retrofitClient = RetrofitClient()

object LoginService :
    LoginAPI by retrofitClient.getService(
        LoginAPI::class.java
        , BaseUrl.URL_WAN_ANDROID
    )

object HomeArticleService :
    HomeAPI.Article by retrofitClient.getService(
        HomeAPI.Article::class.java,
        BaseUrl.URL_WAN_ANDROID
    )