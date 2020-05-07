package com.cya.frame.base.vm

import com.cya.frame.exception.ExceptionEngine
import com.cya.frame.ext.logE
import com.cya.frame.retrofit.BaseResult

/**
 * 主要负责数据获取，来组本地数据库或者远程服务器
 */
open class BaseRepository {

    /**
     * @param errorMsg 会自动传递到BaseResult.Failed
     */
    suspend fun <T : Any> safeCall(
        call: suspend () -> BaseResult<T>,
        errorMsg: String = "获取失败,请稍后重试"
    ): BaseResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            "${e.message}\n$errorMsg".logE()
            with(ExceptionEngine.handleException(e)) {
                BaseResult.Failed(this, errorMsg)
            }
        }
    }

}