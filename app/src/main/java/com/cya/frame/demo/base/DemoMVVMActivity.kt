package com.cya.frame.demo.base

import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.BaseMVVMActivity
import com.cya.frame.demo.base.vm.DemoBaseViewModel

abstract class DemoMVVMActivity<V : ViewBinding, VM : DemoBaseViewModel<*>> :
    BaseMVVMActivity<V, VM>()