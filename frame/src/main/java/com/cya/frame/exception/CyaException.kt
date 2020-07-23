package com.cya.frame.exception


enum class Error(val value: String) {
    /**
     * 未知错误
     */
    UNKNOWN("未知错误"),
    /**
     * 解析错误
     */
    PARSE_ERROR("解析异常"),
    /**
     * 网络错误
     */
    NETWORK_ERROR("服务器链接失败"),
    /**
     * 协议出错
     */
    HTTP_ERROR("请检测你的网络"),
    /**
     * 连接超时
     */
    SOCKET_TIMEOUT("连接超时")
}

/**
 * 业务异常
 */
data class CyaException(val errorCode: Int, val errorMsg: String) :
    Throwable()