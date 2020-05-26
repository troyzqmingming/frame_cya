package com.cya.frame.demo.login.vm

import androidx.lifecycle.MutableLiveData
import com.cya.frame.demo.base.Resource
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.ActivityLoginBinding
import com.jeremyliao.liveeventbus.LiveEventBus

class LoginViewModel(repository: LoginRepository) :
    DemoBaseViewModel<ActivityLoginBinding, LoginRepository>(repository) {

    private val _userState = MutableLiveData<Resource<UserResult>>()

    val userState
        get() = _userState

    fun loginWanAndroid(username: String, password: String) {
        launchResult({
            repository.requestLoginWanAndroid(username, password)
        }, {
            emitUIState(userInfo = it)
            //通知登陆成功
            LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                .post(it)
        }, {
            emitUIState(
                errorMsg = it
            )
        })
    }

    fun registerWanAndroid(username: String, password: String) {
        launchResult({
            repository.requestRegisterWanAndroid(username, password)

        }, {
            emitUIState(
                userInfo = it
            )
            //通知登陆成功
            LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                .post(it)
        }, {
            emitUIState(
                errorMsg = it
            )
        })
    }


    private fun emitUIState(
        userInfo: UserResult? = null,
        errorMsg: String? = null
    ) {
        userState.value = Resource(userInfo, msg = errorMsg)
    }

}