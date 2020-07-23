package com.cya.frame.demo.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.jeremyliao.liveeventbus.LiveEventBus

class LoginViewModel(repository: LoginRepository) :
    DemoBaseViewModel<LoginRepository>(repository) {

    val loginLiveData = MutableLiveData<UserResult>()

    fun loginWanAndroid(username: String, password: String) {
        repository.requestLoginWanAndroid(viewModelScope, username, password) {
            LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                .post(it)
            loginLiveData.postValue(it)
        }
    }

    fun registerWanAndroid(username: String, password: String) {
        repository.requestRegisterWanAndroid(viewModelScope, username, password) {
            LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                .post(it)
            loginLiveData.postValue(it)
        }
    }

}