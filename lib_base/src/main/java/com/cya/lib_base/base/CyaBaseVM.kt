package com.cya.lib_base.base

import com.cya.frame.base.Results
import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.exception.CyaException
import com.cya.frame.exception.Error
import com.cya.frame.ext.no
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.toast
import com.cya.frame.ext.yes
import com.cya.lib_base.entity.HttpBaseResponse

abstract class CyaBaseVM<R : BaseRepository>(repo: R) : BaseViewModel<R>(repo) {

    /**
     * @method
     * @description 处理成功数据，剥离成功数据
     * @author: CYA
     * @param
     * @CreateDate:     2020/9/11 4:20 PM
     * @return
     */
    open fun <T> checkResult(
        result: Results<HttpBaseResponse<T>>,
        successBlock: (t: T?) -> Unit,
        failedBlock: ((code: Int, msg: String) -> Unit)? = null
    ) {
        when (result) {
            // response 成功
            is Results.Success -> {
                // 剥离errorCode = 0 的成功
                (result.data.errorCode == 0).yes {
                    successBlock(result.data.data)
                }.otherwise {
                    failedBlock?.invoke(result.data.errorCode, result.data.errorMsg)
                }
            }
            // 网络异常等
            is Results.Failure -> handlerError(result.error)
        }

    }

    override fun handlerError(e: CyaException) {
        super.handlerError(e)
        (e.errorCode == Error.CANCEL.ordinal).no {
            toast(e.errorMsg)
        }
    }
}
