package com.cya.lib_net.di

import com.cya.lib_net.retorfit.RetrofitClient
import org.koin.dsl.module

val netModule = module {
    single {
        RetrofitClient.instance
    }
}