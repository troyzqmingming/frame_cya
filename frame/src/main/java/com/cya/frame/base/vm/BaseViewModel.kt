package com.cya.frame.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cya.frame.exception.CyaException

abstract class BaseViewModel<R : BaseRepository>(
    val repository: R
) :
    ViewModel() {

    init {
        repository.scope = viewModelScope
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