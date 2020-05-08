package com.cya.frame.base.vm

import com.cya.frame.exception.ExceptionEngine
import com.cya.frame.ext.logE
import com.cya.frame.retrofit.BaseResult

/**
 * 主要负责数据获取，来组本地数据库或者远程服务器
 */
open class BaseRepository {

    suspend fun <T : Any> safeCall(
        call: suspend () -> BaseResult<T>
    ): BaseResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            "Exception msg:\t${e.message}\n".logE()
            with(ExceptionEngine.handleException(e)) {
                BaseResult.Failed(this, "Exception msg:\t${e.message}\n")
            }
        }
    }

}