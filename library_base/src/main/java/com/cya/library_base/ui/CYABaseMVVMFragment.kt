package com.cya.library_base.ui

import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.library_base.vm.CYABaseViewModel

abstract class CYABaseMVVMFragment<V : ViewBinding, VM : CYABaseViewModel<*>> :
    BaseMVVMFragment<V, VM>()