package com.cya.module_user.login

import androidx.lifecycle.MutableLiveData
import com.cya.lib_base.vm.CYABaseViewModel
import com.cya.module_user.result.UserResult
import com.cya.module_user.config.UserConfig
import com.cya.module_user.config.saveUserCache
import com.cya.module_user.data.UserContract
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
                UserConfig.saveUserCache(it)
                LiveEventBus.get(UserContract.EventKey.User.UPDATE_INFO, UserResult::class.java)
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
                UserConfig.saveUserCache(it)
                LiveEventBus.get(UserContract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                    .post(it)
                loginLiveData.postValue(it)
            })
    }

}