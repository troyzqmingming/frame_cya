package com.cya.frame.base

import com.cya.frame.exception.CyaException

/**
  *
  * @Package:        com.cya.frame.base
  * @Description:     通用结果[com.cya.frame.ext.progressApiResponse]
  * @Author:         CYA
  * @CreateDate:     2020/9/11 4:13 PM
 */
sealed class Results<out T> {

    data class Success<out T>(val data: T) : Results<T>()
    data class Failure(val error: CyaException) : Results<Nothing>()
    companion object {
        fun <T> success(result: T): Results<T> = Success(result)
        fun <T> failure(error: CyaException): Results<T> = Failure(error)
    }
}