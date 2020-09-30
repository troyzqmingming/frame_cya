package com.cya.ft_user.login

import androidx.lifecycle.MutableLiveData
import com.cya.ft_user.manager.saveUserCache
import com.cya.lib_base.base.CyaBaseVM
import com.cya.lib_base.contract.EventKey
import com.cya.lib_base.entity.UserResult
import com.cya.lib_base.service.user.wrap.LoginServiceImplWrap
import com.jeremyliao.liveeventbus.LiveEventBus

class LoginVM(repo: LoginRepo) : CyaBaseVM<LoginRepo>(repo) {

    val userLiveData = MutableLiveData<UserResult>()

    fun logout() {
        repository.logoutWanAndroid {
            LoginServiceImplWrap.clearUserCache()
            LiveEventBus.get(EventKey.LOGOUT).post(true)
        }
    }

    fun register(username: String, password: String) {
        launch({ repository.requestRegisterWanAndroid(username, password) }, {
            handlerResult(it) { u ->
                u?.let { it1 -> saveUserCache(it1) }
                userLiveData.postValue(u)
                LiveEventBus.get(EventKey.UPDATE_INFO, UserResult::class.java).post(u)
            }
        })
    }

    fun login(username: String, password: String) {
        launch({
            repository.requestLoginWanAndroid(username, password)
        }, {
            handlerResult(it) { u ->
                u?.let { it1 -> saveUserCache(it1) }
                userLiveData.postValue(u)
                LiveEventBus.get(EventKey.UPDATE_INFO, UserResult::class.java).post(u)
            }
        })
    }
}