package com.cya.module_home.di

import com.cya.module_home.retrofit.api.HomeAPI
import com.cya.module_home.retrofit.service.HomeArticleService
import com.cya.module_home.ui.article.ArticleListRepository
import com.cya.module_home.ui.article.ArticleListViewModel
import com.cya.module_home.ui.mine.MineViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModule = module {
    viewModel { ArticleListViewModel(get()) }
    viewModel { MineViewModel(get()) }
}

val homeRespModule = module {
    single { ArticleListRepository(get()) }
}

val homeApiModule = module {
    single<HomeAPI.Article> {
        HomeArticleService
    }
}
