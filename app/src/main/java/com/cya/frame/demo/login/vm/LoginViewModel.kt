package com.cya.frame.demo.login.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.ActivityLoginBinding
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.coroutines.launch

class LoginViewModel(repository: LoginRepository) :
    DemoBaseViewModel<ActivityLoginBinding, LoginRepository>(repository) {

    private val _uiState = MutableLiveData<UIState>()

    val mUiState
        get() = _uiState

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
        _uiState.value = UIState(showProgress, hideProgress, userInfo, errorMsg)
    }

    data class UIState(
        val showProgress: Boolean,
        val hideProgress: Boolean,
        val userInfo: UserResult?,
        val errorMsg: String?
    )
}