package com.cya.lib_base.base

import com.cya.frame.base.Failed
import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.lib_base.entity.HttpBaseResponse

abstract class CyaBaseVM<R : BaseRepository>(repo: R) : BaseViewModel<R>(repo) {

    /**
     * @description 剥离返回结果
     * @param
     * @return
     * @author zengqingming
     * @time 2020/9/30 9:54 AM
     */
    fun <T> handlerResult(
        result: HttpBaseResponse<T>,
        successBlock: (T?) -> Unit
    ) {
        (result.errorCode == 0).yes {
            successBlock(result.data)
        }.otherwise {
            failedLiveData.postValue(Failed(result.errorCode, result.errorMsg))
        }
    }

}
