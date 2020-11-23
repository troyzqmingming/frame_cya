package com.cya.ft_user.login

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.cya.ft_user.manager.saveUserCache
import com.cya.lib_base.base.CyaBaseVM
import com.cya.lib_base.contract.LOGOUT
import com.cya.lib_base.contract.UPDATE_INFO
import com.cya.lib_base.entity.UserResult
import com.cya.lib_base.service.user.wrap.LoginServiceImplWrap
import com.jeremyliao.liveeventbus.LiveEventBus
import com.orhanobut.logger.Logger

class LoginVM(repo: LoginRepo) : CyaBaseVM<LoginRepo>(repo) {

    val userLiveData = MutableLiveData<UserResult>()

    fun logout() {
        repository.logoutWanAndroid {
            LoginServiceImplWrap.clearUserCache()
            LiveEventBus.get(LOGOUT).post(true)
        }
    }

    fun test() {
        "e".edit {
            this + "hahaha"
        }
    }

    fun register(username: String, password: String) {
        launch({ repository.requestRegisterWanAndroid(username, password) }, {
            handlerResult(it) { u ->
                u?.let { it1 -> saveUserCache(it1) }
                userLiveData.postValue(u)
                LiveEventBus.get(UPDATE_INFO, UserResult::class.java).post(u)
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
                LiveEventBus.get(UPDATE_INFO, UserResult::class.java).post(u)
            }
        })
    }
}

fun String.edit(action: String.() -> String) {
    val a = action(this)
    Logger.e(a)

}