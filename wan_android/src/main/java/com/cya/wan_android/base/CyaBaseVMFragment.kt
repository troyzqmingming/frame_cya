package com.cya.wan_android.base

import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.fragment.BaseVMFragment
import com.cya.frame.base.vm.BaseViewModel

abstract class CyaBaseVMFragment<V : ViewBinding, VM : BaseViewModel<*>> : BaseVMFragment<V, VM>()