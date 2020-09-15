package com.cya.ft_user.di

import com.cya.ft_user.api.UserAPI
import com.cya.ft_user.api.UserService
import com.cya.ft_user.login.LoginRepo
import com.cya.ft_user.login.LoginVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

val userViewModule = module {
    viewModel {
        LoginVM(get())
    }
}

val userApiModule = module {
    single<UserAPI> {
        UserService
    }
}

val userRepoModule = module {
    single {
        LoginRepo(get())
    }
}

