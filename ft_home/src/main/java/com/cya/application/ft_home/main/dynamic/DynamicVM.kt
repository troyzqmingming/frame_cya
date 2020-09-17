package com.cya.application.ft_home.main.dynamic

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.cya.application.ft_home.main.dynamic.entity.DynamicItem
import com.cya.lib_base.base.CyaBaseVM
import kotlinx.coroutines.flow.collect

class DynamicVM(repo: DynamicRepo) : CyaBaseVM<DynamicRepo>(repo) {

    val dynamicList = MutableLiveData<PagingData<DynamicItem>>()

    fun getList() {
        viewModelLaunch {
            val page = repository.getList()
            page.collect {
                dynamicList.postValue(it)
            }
        }
    }
}