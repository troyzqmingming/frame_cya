package com.cya.wan_android.di

import com.cya.wan_android.api.HomeAPI
import com.cya.wan_android.api.HomeService
import com.cya.wan_android.ui.main.home.HomeRepo
import com.cya.wan_android.ui.main.home.HomeVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single<HomeAPI> { HomeService }
}

val vmModule = module {
    viewModel { HomeVM(get()) }
}

val repoModule = module {
    single { HomeRepo(get()) }
}

val appModule = arrayListOf(
    apiModule, vmModule, repoModule
)