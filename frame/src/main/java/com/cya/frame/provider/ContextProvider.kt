package com.cya.frame.provider

import android.content.Context

/**
 * 无侵略式获取context
 */
class ContextProvider private constructor(context: Context) {

    private var mContext = context


    companion object {

        val instance: ContextProvider by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            val context = CyaContextProvider.mContext
                ?: throw  NullPointerException("CyaContextProvider context is null")
            ContextProvider(context)
        }
    }


    fun getContext() = mContext
}