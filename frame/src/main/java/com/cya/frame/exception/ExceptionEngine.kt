package com.cya.frame.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

object ExceptionEngine {

    fun handleException(t: Throwable?): CyaException {
        return when (t) {
            is HttpException -> {
                //均视为网络错误
                CyaException(CyaException.Error.HTTP_ERROR, t, t.code())
            }
            is JsonParseException, is JSONException, is ParseException -> {
                //均视为解析错误
                CyaException(CyaException.Error.PARSE_ERROR, t)
            }
            is ConnectException, is UnknownHostException -> {
                //均视为链接异常
                CyaException(CyaException.Error.NETWORK_ERROR, t)
            }
            else -> {
                CyaException(CyaException.Error.UNKNOWN, t)
            }
        }
    }
}