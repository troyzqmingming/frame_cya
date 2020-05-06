package com.cya.frame.demo.base.vm

import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.demo.bean.HttpBaseResponse
import com.cya.frame.retrofit.BaseResult

open class DemoBaseRepository : BaseRepository() {

    /**
     * 转换返回结果
     */
    open fun <T : Any> convertResponse(response: HttpBaseResponse<T>): BaseResult<T> {
        return when (response.errorCode) {
            0 -> {
                BaseResult.Success(response.data)
            }
            else -> {
                //todo 自定义错误返回, 可以进行清空登陆状态等
                BaseResult.Failed(null, response.errorMsg)
            }
        }
    }
}