package com.cya.frame.demo.di

import com.cya.frame.CyaSDK

private const val ROOT_NAME = "CYA"

/**
 * 私有目录
 */
val FILE_PRIVATE_PATH_ROOT =
    CyaSDK.getContext().getExternalFilesDir(ROOT_NAME)?.absolutePath + ""
