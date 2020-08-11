package com.cya.frame.demo.ui.login

import androidx.lifecycle.MutableLiveData
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.di.Config
import com.cya.frame.demo.ext.saveUserCache
import com.jeremyliao.liveeventbus.LiveEventBus

class LoginViewModel(repository: LoginRepository) :
    DemoBaseViewModel<LoginRepository>(repository) {

    val loginLiveData = MutableLiveData<UserResult>()

    fun loginWanAndroid(username: String, password: String) {
        showLoading()
        launch({
            repository.requestLoginWanAndroid(username, password)
        },
            success = {
                hideLoading()
                Config.Account.saveUserCache(it)
                LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                    .post(it)
                loginLiveData.postValue(it)
            })
    }

    fun registerWanAndroid(username: String, password: String) {
        showLoading()
        launch({
            repository.requestRegisterWanAndroid(username, password)
        },
            success = {
                hideLoading()
                Config.Account.saveUserCache(it)
                LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                    .post(it)
                loginLiveData.postValue(it)
            })
    }

}