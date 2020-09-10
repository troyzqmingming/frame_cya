package com.cya.application.di

import com.cya.application.api.HomeAPI
import com.cya.application.api.HomeService
import com.cya.application.ui.article.ArticleListRepository
import com.cya.application.ui.article.ArticleListViewModel
import com.cya.library_base.di.IModule
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class HomeModule : IModule {

    private val homeViewModule = module {
        viewModel { ArticleListViewModel(get()) }
    }

    private val homeRespModule = module {
        single { ArticleListRepository(get()) }
    }

    private val homeApiModule = module {
        single<HomeAPI> { HomeService }
    }

    override fun getModuleList(): ArrayList<Module> {
        return arrayListOf(homeViewModule, homeRespModule, homeApiModule)
    }

}

