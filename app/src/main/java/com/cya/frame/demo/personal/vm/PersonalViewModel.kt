package com.cya.frame.demo.personal.vm

import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.demo.data.Contract
import com.cya.frame.demo.databinding.FragmentPersonalBinding
import com.cya.frame.demo.login.vm.LoginRepository
import com.jeremyliao.liveeventbus.LiveEventBus

class PersonalViewModel(repository: LoginRepository) :
    BaseViewModel<FragmentPersonalBinding, LoginRepository>(repository) {

    fun logoutUser() {
        repository.logoutWanAndroid {
            LiveEventBus.get(Contract.EventKey.User.LOGOUT).post(true)
        }
    }
}