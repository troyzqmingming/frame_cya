package com.cya.application.ft_home.di

import com.cya.application.ft_home.api.DynamicAPI
import com.cya.application.ft_home.api.DynamicService
import com.cya.application.ft_home.api.HomeAPI
import com.cya.application.ft_home.api.HomeService
import com.cya.application.ft_home.main.dynamic.DynamicRepo
import com.cya.application.ft_home.main.dynamic.DynamicVM
import com.cya.application.ft_home.main.home.HomeRepo
import com.cya.application.ft_home.main.home.HomeVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModule = module {
    viewModel { HomeVM(get()) }
    viewModel { DynamicVM(get()) }
}

val homeApiModule = module {
    single<HomeAPI> { HomeService }
    single<DynamicAPI> { DynamicService }
}

val homeRepoModule = module {
    single { HomeRepo(get()) }
    single { DynamicRepo(get()) }
}
