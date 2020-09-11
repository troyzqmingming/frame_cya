package com.cya.wan_android.ui.login

import androidx.lifecycle.MutableLiveData
import com.cya.wan_android.base.CyaBaseVM
import com.cya.wan_android.entity.UserResult

class LoginVM(repo: LoginRepo) : CyaBaseVM<LoginRepo>(repo) {

    val userLiveData = MutableLiveData<UserResult>()

    fun login(username: String, password: String) {
        viewModelLaunch {
            checkResult(repository.requestLoginWanAndroid(username, password)) {
                userLiveData.postValue(it)
            }
        }
    }
}