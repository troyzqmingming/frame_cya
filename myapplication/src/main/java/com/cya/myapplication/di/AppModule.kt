package com.cya.myapplication.di

import com.cya.application.ft_home.di.homeApiModule
import com.cya.application.ft_home.di.homeRepoModule
import com.cya.application.ft_home.di.homeViewModule
import com.cya.ft_user.di.userApiModule
import com.cya.ft_user.di.userRepoModule
import com.cya.ft_user.di.userViewModule


val appModule = arrayListOf(
    homeViewModule, homeApiModule, homeRepoModule,
    userViewModule, userApiModule, userRepoModule
)