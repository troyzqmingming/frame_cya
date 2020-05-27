package com.cya.frame.base.holder

import androidx.lifecycle.MutableLiveData

class LiveDataHolder {

    private val map = mutableMapOf<Class<*>, MutableLiveData<UIState<*>>>()
    private val listMap = mutableMapOf<Class<*>, MutableLiveData<UIState<MutableList<*>>>>()

    fun <T> getLiveData(type: Class<T>): MutableLiveData<UIState<T>> {
        var liveData = map[type]
        if (liveData == null) {
            liveData = MutableLiveData()
            map[type] = liveData
        }
        return liveData as MutableLiveData<UIState<T>>
    }

    fun <T> getListLiveData(type: Class<T>): MutableLiveData<UIState<MutableList<T>>> {
        var data = listMap[type]
        if (data == null) {
            data = MutableLiveData()
            listMap[type] = data
        }
        return data as MutableLiveData<UIState<MutableList<T>>>
    }
}