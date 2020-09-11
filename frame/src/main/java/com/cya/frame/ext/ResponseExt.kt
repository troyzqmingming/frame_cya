package com.cya.frame.ext

import com.cya.frame.base.Results
import com.cya.frame.exception.CyaException
import com.cya.frame.exception.ExceptionEngine
import retrofit2.Response
import java.lang.Exception

/**
  *
  * @Package:        com.cya.frame.ext
  * @Description:    通用api转换[Results]
  * @Author:         CYA
  * @CreateDate:     2020/9/11 4:14 PM
 */
inline fun <T> progressApiResponse(request: () -> Response<T>): Results<T> {
    return try {
        val response = request()
        response.isSuccessful.yes {
            Results.success(response.body()!!)
        }.otherwise {
            Results.failure(CyaException(response.code(), response.message()))
        }
    } catch (e: Exception) {
        Results.failure(ExceptionEngine.handleException(e))
    }
}