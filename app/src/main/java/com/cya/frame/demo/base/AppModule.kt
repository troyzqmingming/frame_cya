package com.cya.frame.demo.base

import com.cya.frame.demo.home.vm.HomeListRepository
import com.cya.frame.demo.home.vm.HomeListViewModel
import com.cya.frame.demo.login.vm.LoginRepository
import com.cya.frame.demo.login.vm.LoginViewModel
import com.cya.frame.demo.personal.vm.PersonalViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeListViewModel(get()) }
    viewModel { PersonalViewModel(get()) }
}

val repositoryModule = module {
    single { LoginRepository() }
    single { HomeListRepository() }
}

val appModule = listOf(viewModelModule, repositoryModule)