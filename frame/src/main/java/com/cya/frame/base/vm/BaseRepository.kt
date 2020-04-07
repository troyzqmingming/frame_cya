package com.cya.frame.base.vm

import com.cya.frame.retrofit.BaseResult
import com.orhanobut.logger.Logger

/**
 * 主要负责数据获取，来组本地数据库或者远程服务器
 */
open class BaseRepository {

    /**
     * 自动检测异常
     */
    suspend fun <T : Any> safeCall(
        call: suspend () -> BaseResult<T>,
        error: suspend (fail: BaseResult.Failed) -> BaseResult<T>,
        errorMsg: String = "获取失败,请稍后再试"
    ): BaseResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            Logger.e(
                if (e.message.isNullOrEmpty()) {
                    errorMsg
                } else {
                    "${e.message}\n$errorMsg"
                }
            )
            error(BaseResult.Failed(e, errorMsg))
        }
    }
}