package com.cya.frame.base.vm

import com.cya.frame.exception.CyaException
import com.cya.frame.exception.ExceptionEngine
import kotlinx.coroutines.*

/**
 * 主要负责数据获取，来组本地数据库或者远程服务器
 */
open class BaseRepository {

    lateinit var scope: CoroutineScope

    /**
     * 获取数据
     */
    open fun <T> launch(
        block: suspend () -> T,
        success: suspend (T) -> Unit,
        error: ((CyaException) -> Unit)? = null
    ): Job {
        return scope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    block()
                }
            }.onSuccess {
                success(it)
            }.onFailure {
                it.printStackTrace()
                ExceptionEngine.handleException(it).apply {
                    //统一响应错误信息
                    error?.invoke(this)
                }
            }
        }
    }
}