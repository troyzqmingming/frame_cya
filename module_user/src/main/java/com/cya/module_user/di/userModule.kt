package com.cya.module_user.di

import com.cya.module_user.login.LoginRepository
import com.cya.module_user.login.LoginViewModel
import com.cya.module_user.retrofit.api.UserAPI
import com.cya.module_user.retrofit.service.UserService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userViewModule = module {
    viewModel { LoginViewModel(get()) }
}

val userRespModule = module {
    single { LoginRepository(get()) }
}

val userApiModule = module {
    single<UserAPI> {
        UserService
    }
}