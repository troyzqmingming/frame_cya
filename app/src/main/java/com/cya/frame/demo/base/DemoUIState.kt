package com.cya.frame.demo.base

sealed class DemoUIState

object ShowLoading : DemoUIState()

object HideLoading : DemoUIState()

class Resource<T>(
    // 数据
    var data: T? = null,
    //成功或者错误的 code 以及 message
    var code: Int? = null,
    var msg: String? = null,
    //预留字段
    var ext: Any? = null
) : DemoUIState()
