package com.cya.frame.base.ui

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.vm.BaseViewModel

abstract class BaseMVVMFragment<V : ViewBinding, VM : BaseViewModel<*>> : BaseFragment<V>() {


    lateinit var vm: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm = initViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initViewModel(): VM
}