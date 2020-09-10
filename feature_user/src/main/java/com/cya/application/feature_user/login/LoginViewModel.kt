package com.cya.application.feature_user.login

import androidx.lifecycle.MutableLiveData
import com.cya.application.feature_user.bean.UserResult
import com.cya.application.feature_user.config.saveUserCache
import com.cya.application.feature_user.contract.UserKeys
import com.cya.library_base.vm.CYABaseViewModel
import com.jeremyliao.liveeventbus.LiveEventBus

class LoginViewModel(repository: LoginRepository) :
    CYABaseViewModel<LoginRepository>(repository) {

    val loginLiveData = MutableLiveData<UserResult>()

    fun loginWanAndroid(username: String, password: String) {
        showLoading()
        launch({
            repository.requestLoginWanAndroid(username, password)
        },
            success = {
                hideLoading()
                saveUserCache(it)
                LiveEventBus.get(UserKeys.EventKey.UPDATE_INFO, UserResult::class.java)
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
                saveUserCache(it)
                LiveEventBus.get(UserKeys.EventKey.UPDATE_INFO, UserResult::class.java)
                    .post(it)
                loginLiveData.postValue(it)
            })
    }

}