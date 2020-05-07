package com.cya.frame.retrofit

import com.cya.frame.exception.CyaException

/**
 * 用于接口数据返回
 */
sealed class BaseResult<out T : Any> {
    /**
     * 成功
     */
    data class Success<out T : Any>(val data: T?) : BaseResult<T>()

    /**
     * 失败
     * @param exception 异常
     * @param errorMsg 自定义错误msg
     */
    data class Failed(
        val exception: CyaException?,
        val errorMsg: String
    ) : BaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "SUCCESS data->[${data}]"
            is Failed -> "FAILED exception->[${exception}\nerrorMsg->[$errorMsg]]"
        }
    }

}