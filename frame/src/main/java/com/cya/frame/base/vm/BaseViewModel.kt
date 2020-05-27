package com.cya.frame.base.vm

import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.holder.LiveDataHolder
import com.cya.frame.base.holder.UIState

abstract class BaseViewModel<V : ViewBinding, R : BaseRepository>(
    val repository: R
) :
    ViewModel() {

    //使用map管理LiveData
    private val holder = LiveDataHolder()

    /**
     * 返回<T>
     */
    fun <T> getObservable(type: Class<T>) = run {
        holder.getLiveData(type)
    }

    /**
     * 返回list<T>
     */
    fun <T> getListObservable(type: Class<T>) = kotlin.run {
        holder.getListLiveData(type)
    }

    /**
     * 发射<T>
     */
    fun <T> emit(type: Class<T>, value: UIState<T>) {
        getObservable(type).value = value
    }

    /**
     * 发射list<T>
     */
    fun <T> emit2(type: Class<T>, value: UIState<MutableList<T>>) {
        getListObservable(type).value = value
    }
}