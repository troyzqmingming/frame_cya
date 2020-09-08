package com.cya.lib_base.ui

import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.BaseMVVMActivity
import com.cya.lib_base.vm.CYABaseViewModel

abstract class CYABaseMVVMActivity<V : ViewBinding, VM : CYABaseViewModel<*>> :
    BaseMVVMActivity<V, VM>()