package com.cya.wan_android.ui.login

import androidx.lifecycle.MutableLiveData
import com.cya.wan_android.base.CyaBaseVM
import com.cya.wan_android.contract.EventKey
import com.cya.wan_android.entity.UserResult
import com.cya.wan_android.manager.saveUserCache
import com.jeremyliao.liveeventbus.LiveEventBus

class LoginVM(repo: LoginRepo) : CyaBaseVM<LoginRepo>(repo) {

    val userLiveData = MutableLiveData<UserResult>()

    fun logout() {
        repository.logoutWanAndroid {
            LiveEventBus.get(EventKey.LOGOUT).post(true)
        }
    }

    fun register(username: String, password: String) {
        viewModelLaunch {
            checkResult(repository.requestRegisterWanAndroid(username, password)) { uInfo ->
                uInfo?.let { it1 ->
                    saveUserCache(it1)
                    LiveEventBus.get(EventKey.UPDATE_INFO, UserResult::class.java).post(it1)
                }
                userLiveData.postValue(uInfo)
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelLaunch {
            checkResult(repository.requestLoginWanAndroid(username, password)) { uInfo ->
                uInfo?.let { it1 ->
                    saveUserCache(it1)
                    LiveEventBus.get(EventKey.UPDATE_INFO, UserResult::class.java).post(it1)
                }
                userLiveData.postValue(uInfo)
            }
        }
    }
}