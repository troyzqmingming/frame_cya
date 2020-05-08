package com.cya.frame.demo.base.vm

import androidx.lifecycle.viewModelScope
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.exception.CyaException
import com.cya.frame.retrofit.BaseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class DemoBaseViewModel<V : ViewBinding, R : BaseRepository>(repository: R) :
    BaseViewModel<V, R>(repository) {

    fun <T : Any> launchResult(
        block: suspend CoroutineScope.() -> BaseResult<T>,
        success: (T?) -> Unit,
        failed: (String) -> Unit,
        exception: ((CyaException) -> Unit)? = null
    ) {
        viewModelScope.launch {
            checkResult(block(), success, failed, exception ?: {
                failed(
                    when (it.error) {
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
                            "未知异常:${it.throwable?.message}"
                        }
                    }
                )
            })
        }

    }
}