package com.cya.module_user.retrofit.service

import com.cya.lib_base.constant.BaseUrl
import com.cya.lib_net.retorfit.RetrofitClient
import com.cya.module_user.retrofit.api.UserAPI


object UserService :
    UserAPI by RetrofitClient.instance.getService(
        UserAPI::class.java,
        BaseUrl.URL_WAN_ANDROID
    )