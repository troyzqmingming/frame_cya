package com.cya.lib_base.base

import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.activity.BaseVMActivity
import com.cya.frame.base.vm.BaseViewModel

abstract class CyaBaseVMActivity<V : ViewBinding, VM : BaseViewModel<*>> : BaseVMActivity<V, VM>()