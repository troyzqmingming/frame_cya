package com.cya.frame.demo.di

import com.cya.frame.demo.base.vm.DemoBaseRepository
import com.cya.frame.demo.ui.article.ArticleListRepository
import com.cya.frame.demo.ui.article.ArticleListViewModel
import com.cya.frame.demo.ui.login.LoginRepository
import com.cya.frame.demo.ui.login.LoginViewModel
import com.cya.frame.demo.ui.mine.MineViewModel
import com.cya.frame.demo.service.HomeArticleService
import com.cya.frame.demo.service.LoginService
import com.cya.frame.demo.service.api.HomeAPI
import com.cya.frame.demo.service.api.LoginAPI
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ArticleListViewModel(get()) }
    viewModel { MineViewModel(get()) }
}

val repositoryModule = module {
    single { DemoBaseRepository() }
    single { LoginRepository(get()) }
    single { ArticleListRepository(get()) }
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