package com.cya.library_base.di

import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import org.koin.core.module.Module

/**
 * module的di类路径
 */
internal val moduleClassPathArr = arrayOf(
    "com.cya.application.feature_user.di.UserModule",
    "com.cya.application.di.HomeModule"
)

/**
 * @method
 * @description 获取module的di
 * @author: CYA
 * @param
 * @CreateDate:     2020/9/9 4:46 PM
 * @return
 */
internal fun getModuleDI(clzName: String): ArrayList<Module>? {
    val mClass = Class.forName(clzName)
    val instance = mClass.newInstance()
    val method = mClass.getMethod("getModuleList")
    val list = method.invoke(instance)
    (list == null).yes {
        return null
    }.otherwise {
        return list as ArrayList<Module>
    }
}

fun getAllModuleList(): ArrayList<Module> {
    val allList = arrayListOf<Module>()
    moduleClassPathArr.forEach { path ->
        getModuleDI(path)?.let {
            allList.addAll(it)
        }
    }
    return allList
}

interface IModule {
    fun getModuleList(): ArrayList<Module>
}