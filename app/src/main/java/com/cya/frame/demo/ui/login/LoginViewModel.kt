package com.cya.frame.demo.ui.login

import androidx.lifecycle.MutableLiveData
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.jeremyliao.liveeventbus.LiveEventBus

class LoginViewModel(repository: LoginRepository) :
    DemoBaseViewModel<LoginRepository>(repository) {

    val loginLiveData = MutableLiveData<UserResult>()

    fun loginWanAndroid(username: String, password: String) {
        showLoading()
        repository.requestLoginWanAndroid(username, password, {
            hideLoading()
            LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                .post(it)
            loginLiveData.postValue(it)
        }) {
            handlerError(it)
        }
    }

    fun registerWanAndroid(username: String, password: String) {
        showLoading()
        repository.requestRegisterWanAndroid(username, password, {
            hideLoading()
            LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                .post(it)
            loginLiveData.postValue(it)
        }) {
            handlerError(it)
        }

    }

}