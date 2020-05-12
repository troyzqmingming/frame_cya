package com.cya.frame.demo.di

import com.cya.frame.demo.home.vm.HomeListRepository
import com.cya.frame.demo.home.vm.HomeListViewModel
import com.cya.frame.demo.login.vm.LoginRepository
import com.cya.frame.demo.login.vm.LoginViewModel
import com.cya.frame.demo.personal.vm.PersonalViewModel
import com.cya.frame.demo.service.HomeArticleService
import com.cya.frame.demo.service.LoginService
import com.cya.frame.demo.service.api.HomeAPI
import com.cya.frame.demo.service.api.LoginAPI
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeListViewModel(get()) }
    viewModel { PersonalViewModel(get()) }
}

val repositoryModule = module {
    single { LoginRepository(get()) }
    single { HomeListRepository(get()) }
}

val remoteModule = module {
    single<LoginAPI> {
        LoginService
    }
    single<HomeAPI.Article> {
        HomeArticleService
    }
}

val appModule = listOf(
    viewModelModule,
    repositoryModule,
    remoteModule
)