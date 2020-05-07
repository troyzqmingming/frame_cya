package com.cya.frame.exception

/**
 * @param httpCode 只有error=HTTP_ERROR才有值
 */
data class CyaException(var error: Error, var throwable: Throwable? = null, var httpCode: Int = 0) {

    enum class Error {
        /**
         * 未知错误
         */
        UNKNOWN,
        /**
         * 解析错误
         */
        PARSE_ERROR,
        /**
         * 网络错误
         */
        NETWORK_ERROR,
        /**
         * 协议出错
         */
        HTTP_ERROR
    }

}