package com.cya.wan_android.base

import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.frame.base.vm.BaseViewModel

abstract class CyaBaseVMFragment<V : ViewBinding, VM : BaseViewModel<*>> : BaseMVVMFragment<V, VM>()