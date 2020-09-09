package com.cya.application.feature_user.di

import com.cya.application.feature_user.api.UserAPI
import com.cya.application.feature_user.api.UserService
import com.cya.application.feature_user.login.LoginRepository
import com.cya.application.feature_user.login.LoginViewModel
import com.cya.library_base.di.IModule
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class UserModule : IModule {

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

    override fun getModuleList(): ArrayList<Module> {
        return arrayListOf(userViewModule, userRespModule, userApiModule)
    }
}



