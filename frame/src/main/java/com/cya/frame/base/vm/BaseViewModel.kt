package com.cya.frame.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cya.frame.base.Failed
import com.cya.frame.exception.CyaException
import com.cya.frame.exception.ExceptionEngine
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<R : BaseRepository>(
    val repository: R
) :
    ViewModel() {

    @Deprecated("使用launch 已实现flow")
    open fun <T> viewModelLaunch(block: suspend () -> T) {
        viewModelScope.launch {
            showLoading()
            block()
            hideLoading()
        }
    }

    open fun <T> launch(
        block: suspend () -> T,
        result: (T) -> Unit,
        error: ((CyaException) -> Unit)? = null
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            showLoading()
            flow {
                emit(block())
            }
                .flowOn(Dispatchers.IO)
                .catch { t: Throwable ->
                    hideLoading()
                    ExceptionEngine.handleException(t)
                        .apply {
                            (error == null).yes {
                                errorLiveData.postValue(this)
                            }.otherwise {
                                error?.invoke(this)
                            }
                        }
                }
                .collect {
                    hideLoading()
                    result(it)
                }


        }

    }

    /**
     * 错误信息
     */
    val errorLiveData = MutableLiveData<CyaException>()

    /**
     * 失败信息
     */
    val failedLiveData = MutableLiveData<Failed>()

    /**
     * 无更多数据
     */
    val noMoreLiveData = MutableLiveData<Any>()

    /**
     * 无数据
     */
    val emptyLiveData = MutableLiveData<Any>()

    /**
     * 进度
     */
    val loadingLiveData = MutableLiveData<Loading>()

    fun showLoading() {
        loadingLiveData.postValue(SHOW)
    }

    fun hideLoading() {
        loadingLiveData.postValue(HIDE)
    }
}

sealed class Loading

object SHOW : Loading()

object HIDE : Loading()