package com.cya.frame.demo.login.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.ActivityLoginBinding
import com.google.gson.Gson
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LoginViewModel(repository: LoginRepository) :
    BaseViewModel<ActivityLoginBinding, LoginRepository>(repository) {

    val mUiState = MutableLiveData<UIState>()


    fun login(phone: String, code: String) {
        viewModelScope.launch {
            val response =
                repository.loginAndGetUserInfo(
                    phone,
                    code
                )
            checkResult(response, {
                //update ui
                emitUIState(
                    hideProgress = true,
                    userInfo = it
                )
                //通知登陆成功
                LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                    .post(it)
            }, { i, j ->
                //update ui
                emitUIState(
                    hideProgress = true,
                    errorMsg = j
                )
            })
        }
    }

    fun loginWanAndroid(username: String, password: String) {
        viewModelScope.launch {
            emitUIState(
                hideProgress = true
            )
            val response = repository.loginWanAndroid(username, password)
            checkResult(response, {
                val userInfo = it?.nickname?.let { it1 ->
                    UserResult(it1)

                }
                repository.isLogin = true
                repository.userInfoData = Gson().toJson(userInfo)
                emitUIState(
                    hideProgress = true,
                    userInfo = userInfo
                )
                //通知登陆成功
                LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                    .post(userInfo)
            }, { i, j ->
                emitUIState(
                    hideProgress = true,
                    errorMsg = j
                )
            })
        }
    }

    fun registerWanAndroid(username: String, password: String) {
        viewModelScope.launch {
            val response = repository.registerWanAndroid(username, password)
            checkResult(response, {
                val userInfo = it?.nickname?.let { it1 -> UserResult(it1) }
                emitUIState(
                    hideProgress = true,
                    userInfo = userInfo
                )
                //通知登陆成功
                LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                    .post(userInfo)
            }, { i, j ->
                emitUIState(
                    hideProgress = true,
                    errorMsg = j
                )
            })
        }
    }


    private fun emitUIState(
        showProgress: Boolean = false,
        hideProgress: Boolean = false,
        userInfo: UserResult? = null,
        errorMsg: String? = null
    ) {
        mUiState.value = UIState(showProgress, hideProgress, userInfo, errorMsg)
    }

    data class UIState(
        val showProgress: Boolean,
        val hideProgress: Boolean,
        val userInfo: UserResult?,
        val errorMsg: String?
    )
}