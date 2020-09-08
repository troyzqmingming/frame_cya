package com.cya.lib_base.vm

import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.exception.CyaException
import com.cya.frame.ext.toast

abstract class CYABaseViewModel<R : BaseRepository>(repository: R) :
    BaseViewModel<R>(repository) {

    override fun handlerError(e: CyaException) {
        super.handlerError(e)
        toast(e.errorMsg)
    }
}

