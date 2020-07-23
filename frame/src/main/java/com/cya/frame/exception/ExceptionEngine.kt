package com.cya.frame.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ExceptionEngine {

    fun handleException(t: Throwable?): CyaException {
        return when (t) {
            is HttpException -> {
                //均视为网络错误
                CyaException(Error.HTTP_ERROR.ordinal, Error.HTTP_ERROR.value)
            }
            is JsonParseException, is JSONException, is ParseException -> {
                //均视为解析错误
                CyaException(Error.PARSE_ERROR.ordinal, Error.PARSE_ERROR.value)
            }
            is SocketTimeoutException -> {
                CyaException(Error.SOCKET_TIMEOUT.ordinal, Error.SOCKET_TIMEOUT.value)
            }
            is ConnectException, is UnknownHostException -> {
                //均视为链接异常
                CyaException(Error.NETWORK_ERROR.ordinal, Error.NETWORK_ERROR.value)
            }
            is CyaException -> {
                t
            }
            else -> {
                CyaException(Error.UNKNOWN.ordinal, Error.UNKNOWN.value)
            }
        }
    }
}