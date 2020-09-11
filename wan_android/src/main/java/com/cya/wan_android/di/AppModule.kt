package com.cya.wan_android.di

import com.cya.wan_android.api.HomeAPI
import com.cya.wan_android.api.HomeService
import com.cya.wan_android.api.UserAPI
import com.cya.wan_android.api.UserService
import com.cya.wan_android.ui.login.LoginRepo
import com.cya.wan_android.ui.login.LoginVM
import com.cya.wan_android.ui.main.home.HomeRepo
import com.cya.wan_android.ui.main.home.HomeVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single<HomeAPI> { HomeService }
    single<UserAPI> { UserService }
}

val vmModule = module {
    viewModel { HomeVM(get()) }
    viewModel { LoginVM(get()) }
}

val repoModule = module {
    single { HomeRepo(get()) }
    single { LoginRepo(get()) }
}

val appModule = arrayListOf(
    apiModule, vmModule, repoModule
)