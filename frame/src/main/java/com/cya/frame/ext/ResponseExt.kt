package com.cya.frame.ext

import com.cya.frame.base.Results
import com.cya.frame.exception.ExceptionEngine

/**
 *
 * @Package:        com.cya.frame.ext
 * @Description:    通用api转换[Results]
 * @Author:         CYA
 * @CreateDate:     2020/9/11 4:14 PM
 */
inline fun <T> progressApiResponse(request: () -> T): Results<T> {
    return try {
        Results.success(request())
    } catch (e: Exception) {
        Results.failure(ExceptionEngine.handleException(e))
    }
}