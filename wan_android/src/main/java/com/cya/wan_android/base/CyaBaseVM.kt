package com.cya.wan_android.base

import com.cya.frame.base.Results
import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.exception.CyaException
import com.cya.frame.ext.toast
import com.cya.wan_android.entity.HttpBaseResponse

abstract class CyaBaseVM<R : BaseRepository>(repo: R) : BaseViewModel<R>(repo) {

    /**
     * @method
     * @description 处理成功数据，剥离成功数据
     * @author: CYA
     * @param
     * @CreateDate:     2020/9/11 4:20 PM
     * @return
     */
    fun <T> checkResult(
        result: Results<HttpBaseResponse<T>>,
        successBlock: (t: T?) -> Unit
    ) {
        when (result) {
            // response 成功
            is Results.Success -> {
                // 剥离errorCode = 0 的成功
                when (val dataRes = result.data.build()) {
                    is Results.Success -> successBlock(dataRes.data.data)
                    is Results.Failure -> handlerError(dataRes.error)
                }
            }
            is Results.Failure -> handlerError(result.error)
        }
    }

    override fun handlerError(e: CyaException) {
        super.handlerError(e)
        toast(e.errorMsg)
    }
}
