package com.cya.frame.demo.base

import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.frame.base.vm.BaseViewModel

abstract class DemoBaseMVVMFragment<V : ViewBinding, VM : BaseViewModel<V, *>> :
    BaseMVVMFragment<V, VM>()