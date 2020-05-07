package com.cya.frame.utils

import java.io.File
import java.text.DecimalFormat

/**
 * 获取文件夹大小
 */
fun getFolderSize(file: File): Long {
    var totalSize = 0L
    for (indexFile in file.listFiles()) {
        totalSize += if (indexFile.isFile) {
            indexFile.length()
        } else {
            getFolderSize(indexFile)
        }
    }
    return totalSize
}

/**
 * 格式化大小
 */
fun formatFileSize(size: Long, unit: Int = 1024): String {
    val format = DecimalFormat("#.00")
    return when {
        size < 0 -> "0B"
        size < unit -> format.format(size.toDouble()) + "B"
        size < unit * unit -> format.format(size.toDouble() / unit) + "KB"
        size < unit * unit * unit -> format.format(size.toDouble() / unit / unit) + "MB"
        else -> format.format(size.toDouble() / unit / unit / unit) + "GB"
    }
}