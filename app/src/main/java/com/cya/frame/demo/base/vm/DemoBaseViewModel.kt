package com.cya.frame.demo.base.vm

import androidx.viewbinding.ViewBinding
import com.cya.frame.CyaSDK
import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.exception.CyaException
import com.cya.frame.ext.toast
import com.cya.frame.retrofit.BaseResult

abstract class DemoBaseViewModel<V : ViewBinding, R : BaseRepository>(repository: R) :
    BaseViewModel<V, R>(repository) {

    /**
     * 统一处理异常
     */
    override fun <T : Any> checkResult(
        result: BaseResult<T>,
        success: (T?) -> Unit,
        failed: (CyaException?, String?) -> Unit
    ) {
        if (result is BaseResult.Failed && result.exception != null) {
            val exceptionMsg = when (result.exception?.error) {
                CyaException.Error.HTTP_ERROR -> {
                    "请检测你的网络"
                }
                CyaException.Error.PARSE_ERROR -> {
                    "解析异常"
                }
                CyaException.Error.NETWORK_ERROR -> {
                    "服务器链接失败"
                }
                else -> {
                    "未知异常:${result.exception?.throwable?.message}"
                }
            }
            failed(result.exception, exceptionMsg)
        } else {
            super.checkResult(result, success, failed)
        }
    }

}