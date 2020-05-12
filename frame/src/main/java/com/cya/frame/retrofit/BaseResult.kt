package com.cya.frame.retrofit


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
     * @param errorMsg 自定义错误msg
     */
    data class Failed(
        val errorMsg: String
    ) : BaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "SUCCESS data->[${data}]"
            is Failed -> "FAILED exception->[$errorMsg->[$errorMsg]]"
        }
    }

}