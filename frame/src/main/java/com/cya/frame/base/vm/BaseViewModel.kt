package com.cya.frame.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cya.frame.exception.CyaException
import com.cya.frame.exception.ExceptionEngine
import kotlinx.coroutines.*

abstract class BaseViewModel<R : BaseRepository>(
    val repository: R
) :
    ViewModel() {

    /**
     * 获取数据
     */
    open fun <T> launch(
        block: suspend () -> T,
        success: suspend (T) -> Unit,
        error: ((CyaException) -> Unit)? = null
    ): Job {
        return viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    block()
                }
            }.onSuccess {
                success(it)
            }.onFailure {
                it.printStackTrace()
                ExceptionEngine.handleException(it).apply {
                    //统一响应错误信息
                    if (error == null) {
                        handlerError(this)
                    } else {
                        error.invoke(this)
                    }
                }
            }
        }
    }

    open fun handlerError(e: CyaException) {
        errorLiveData.postValue(e)
        hideLoading()
    }

    /**
     * 错误信息
     */
    val errorLiveData = MutableLiveData<CyaException>()
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