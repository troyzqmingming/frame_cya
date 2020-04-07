package com.cya.frame

import com.cya.frame.provider.ContextProvider
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

object CyaSDK {

    /**
     * @param loggerTag tag
     */
    fun initApplication(
        loggerTag: String = CyaSDK::class.java.simpleName
    ) {
        val format = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)
            .tag(loggerTag).build()
        Logger.addLogAdapter(AndroidLogAdapter(format))
    }

    fun getContext() = ContextProvider.instance.getContext()

}