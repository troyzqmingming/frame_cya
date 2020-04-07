package com.cya.frame.retrofit

import com.cya.frame.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseRetrofitClient {

    companion object {
        private const val TIME_OUT = 5
    }

    /**
     * 默认使用自定义打印
     * FormUrlEncoded 可以打印
     */
    private var isOkHttpLogger: Boolean = false

    fun useOkHttpLogging(): BaseRetrofitClient {
        isOkHttpLogger = true
        return this
    }

    private fun getDefaultLogger(): LoggingInterceptor {
        return LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("Request")
            .response("Response").build()
    }

    private fun getOkHttpLogger(): HttpLoggingInterceptor {
        val oKHttpLogger = HttpLoggingInterceptor()
        oKHttpLogger.level = HttpLoggingInterceptor.Level.BODY
        return oKHttpLogger
    }

    private fun genClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(
            if (isOkHttpLogger) {
                getOkHttpLogger()
            } else {
                getDefaultLogger()
            }
        )
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        handleBuilder(builder)
        return builder.build()
    }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return retrofit2.Retrofit.Builder()
            .client(genClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(serviceClass)
    }
}