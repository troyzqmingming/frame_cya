package com.cya.lib_base.entity

import com.cya.frame.base.Results
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
    fun build(): Results<HttpBaseResponse<T>> {
        return when (errorCode) {
            0 -> {
                Results.success(this)
            }
            else -> {
                Results.failure(CyaException(errorCode, errorMsg))
            }
        }
    }
}