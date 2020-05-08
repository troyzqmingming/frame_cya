package com.cya.frame.demo.personal.vm

import androidx.lifecycle.MutableLiveData
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.FragmentPersonalBinding
import com.cya.frame.demo.login.vm.LoginRepository
import com.google.gson.Gson
import com.jeremyliao.liveeventbus.LiveEventBus

class PersonalViewModel(repository: LoginRepository) :
    BaseViewModel<FragmentPersonalBinding, LoginRepository>(repository) {

    var uiState = MutableLiveData<UIState>()


    init {
        emitUIState(
            repository.isLogin, if (repository.userInfoData.isEmpty()) {
                null
            } else {
                Gson().fromJson(repository.userInfoData, UserResult::class.java)
            }
        )
    }

    fun logoutUser() {
        repository.logoutWanAndroid {
            LiveEventBus.get(Contract.EventKey.User.LOGOUT).post(true)
        }
    }

    private fun emitUIState(isLogin: Boolean = false, userResult: UserResult? = null) {
        uiState.value = UIState(isLogin, userResult)
    }


    data class UIState(var isLogin: Boolean, var userResult: UserResult?)
}