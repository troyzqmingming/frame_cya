package com.cya.frame.base.holder

enum class State {
    SUCCESS,
    FAILED,
}

/**
 * 通用liveData bean
 */
open class UIState<T>(
    var state: State = State.SUCCESS,
    // 数据
    var data: T? = null,
    //成功或者错误的 code 以及 message
    var code: Int? = null,
    var msg: String? = null,
    //预留字段
    var ext: Any? = null
)