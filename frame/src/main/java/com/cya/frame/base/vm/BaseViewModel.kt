package com.cya.frame.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cya.frame.exception.CyaException

abstract class BaseViewModel<R : BaseRepository>(
    val repository: R
) :
    ViewModel() {

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
}