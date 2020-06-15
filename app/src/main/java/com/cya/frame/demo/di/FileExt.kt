package com.cya.frame.demo.di

import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes

/**
 * 截取文件名，
 * @param url
 * @param customName 如截取失败，使用自定义文件名
 */
fun getFileNameFormUrl(url: String, customName: String = ""): String {
    return url.split("/").run {
        return@run (size > 0).yes {
            //截取文件名
            this[size - 1]
        }.otherwise {
            customName
        }
    }
}