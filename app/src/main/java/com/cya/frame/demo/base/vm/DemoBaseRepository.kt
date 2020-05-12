package com.cya.frame.demo.base.vm

import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.demo.bean.HttpBaseResponse
import com.cya.frame.retrofit.BaseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

open class DemoBaseRepository : BaseRepository() {

    /**
     * 转换返回结果
     */
    open suspend fun <T : Any> executeResponse(
        response: HttpBaseResponse<T>,
        successBlock: (suspend CoroutineScope.(T?) -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.(String) -> Unit)? = null
    ): BaseResult<T> {
        return coroutineScope {
            when (response.errorCode) {
                0 -> {
                    successBlock?.invoke(this, response.data)
                    BaseResult.Success(response.data)
                }
                else -> {
                    //todo 自定义错误返回, 可以进行清空登陆状态等
                    errorBlock?.invoke(this, response.errorMsg)
                    BaseResult.Failed(response.errorMsg)
                }
            }
        }
    }
}