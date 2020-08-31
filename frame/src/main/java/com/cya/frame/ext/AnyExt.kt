package com.cya.frame.ext

import java.io.File
import kotlin.random.Random

/**
 * 随检颜色
 */
fun Any.randomColor(): Int {
    return -0x1000000 or Random.nextInt(0x00ffffff)
}

/**
 * 创建文件
 */
fun Any.createFile(path: String, name: String): File {
    File(path).apply {
        (this.exists()).no {
            this.mkdirs()
        }
        return File(path, name)
    }

}