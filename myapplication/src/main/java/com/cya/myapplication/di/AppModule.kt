package com.cya.myapplication.di

import com.cya.lib_net.di.netModule
import com.cya.module_home.di.homeApiModule
import com.cya.module_home.di.homeRespModule
import com.cya.module_home.di.homeViewModule
import com.cya.module_user.di.userApiModule
import com.cya.module_user.di.userRespModule
import com.cya.module_user.di.userViewModule


val appModule = arrayListOf(
    netModule,
    homeViewModule, homeRespModule, homeApiModule,
    userViewModule, userRespModule, userApiModule

)