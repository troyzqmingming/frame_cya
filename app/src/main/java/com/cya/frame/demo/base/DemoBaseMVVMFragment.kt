package com.cya.frame.demo.base

import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.frame.demo.base.vm.DemoBaseViewModel

abstract class DemoBaseMVVMFragment<V : ViewBinding, VM : DemoBaseViewModel<*>> :
    BaseMVVMFragment<V, VM>()