package com.cya.frame.demo.base.vm

import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.base.vm.Error
import com.cya.frame.ext.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

open class DemoBaseRepository : BaseRepository() {

    override fun <T> launch(
        scope: CoroutineScope,
        block: suspend () -> T,
        success: suspend (T) -> Unit,
        error: Error?
    ): Job {
        return super.launch(scope, block, success, error ?: {
            toast(it.errorMsg)
        })
    }
}