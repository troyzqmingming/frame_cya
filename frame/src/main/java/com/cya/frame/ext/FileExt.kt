package com.cya.frame.ext

import com.cya.frame.utils.*
import java.io.File

/**
 * 总大小
 */
val File.totalSize: Long
    get() = if (isFile) length() else getFolderSize(this)

/**
 * 格式化大小。100MB
 */
val File.formatSize: String
    get() = formatFileSize(length())



