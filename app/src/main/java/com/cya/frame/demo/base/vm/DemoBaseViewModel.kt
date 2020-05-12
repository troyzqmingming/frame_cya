package com.cya.frame.demo.base.vm

import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.demo.base.DemoUIState
import com.cya.frame.demo.base.HideLoading
import com.cya.frame.demo.base.ShowLoading
import com.cya.frame.exception.CyaException
import com.cya.frame.exception.ExceptionEngine
import com.cya.frame.retrofit.BaseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class DemoBaseViewModel<V : ViewBinding, R : BaseRepository>(repository: R) :
    BaseViewModel<V, R>(repository) {
    /**
     * 通用数据状态
     */
    data class DataState<T>(var data: T?, var errorMsg: String?)

    val demoUIState = MutableLiveData<DemoUIState>()

    fun <T : Any> launchResult(
        block: suspend CoroutineScope.() -> BaseResult<T>,
        successBlock: (T?) -> Unit,
        failedBlock: (String) -> Unit,
        exceptionBlock: ((CyaException) -> Unit)? = null,
        completeBlock: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            try {
                demoUIState.value = ShowLoading
                val result = block()
                demoUIState.value = HideLoading
                when (result) {
                    is BaseResult.Success -> {
                        successBlock(result.data)
                    }
                    is BaseResult.Failed -> {
                        failedBlock(result.errorMsg)
                    }
                }
            } catch (e: Exception) {
                demoUIState.value = HideLoading
                val cyaException = ExceptionEngine.handleException(e)
                if (exceptionBlock == null) {
                    failedBlock(executeException(cyaException))
                } else {
                    exceptionBlock.invoke(cyaException)
                }

            } finally {
                demoUIState.value = HideLoading
                completeBlock?.invoke()
            }
        }
    }

    /**
     * 统一处理异常
     */
    private fun executeException(cyaException: CyaException): String {
        return when (cyaException.error) {
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
                "未知异常:${cyaException.throwable?.message}"
            }
        }
    }
}