package com.cya.lib_base.bean

import com.cya.frame.exception.CyaException
import com.google.gson.annotations.SerializedName

/**
 * 统一接收
 */
data class HttpBaseResponse<T>(
    @SerializedName("error_code", alternate = ["errorCode"])
    var errorCode: Int,
    @SerializedName("message", alternate = ["errorMsg"])
    var errorMsg: String = "",
    @SerializedName("data")
    var data: T? = null
) {
    fun build(): T? {
        when (errorCode) {
            0 -> {
                return data
            }
            else -> {
                throw CyaException(errorCode, errorMsg)
            }
        }
    }
}