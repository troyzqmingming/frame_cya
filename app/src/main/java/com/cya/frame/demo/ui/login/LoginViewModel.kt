package com.cya.frame.demo.ui.login

import com.cya.frame.base.holder.UIState
import com.cya.frame.base.holder.State
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.bean.result.UserResult
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.FragmentLoginBinding
import com.jeremyliao.liveeventbus.LiveEventBus

class LoginViewModel(repository: LoginRepository) :
    DemoBaseViewModel<FragmentLoginBinding, LoginRepository>(repository) {

    var testCount = 0
    fun testButton() {
        emit(Int::class.java, UIState(data = ++testCount))
    }

    fun loginWanAndroid(username: String, password: String) {
        launchResult({
            repository.requestLoginWanAndroid(username, password)
        }, {
            emitUIState(userInfo = it)
            //通知登陆成功
            LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                .post(it)
        }, {
            emitUIState(state = State.FAILED, errorMsg = it)
        })
    }

    fun registerWanAndroid(username: String, password: String) {
        launchResult({
            repository.requestRegisterWanAndroid(username, password)

        }, {
            emitUIState(userInfo = it)
            //通知登陆成功
            LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                .post(it)
        }, {
            emitUIState(state = State.FAILED, errorMsg = it)
        })
    }


    private fun emitUIState(
        state: State = State.SUCCESS,
        userInfo: UserResult? = null,
        errorMsg: String? = null
    ) {
        emit(UserResult::class.java, UIState(state = state, data = userInfo, msg = errorMsg))
    }

}