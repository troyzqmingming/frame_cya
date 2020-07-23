package com.cya.frame.base.ui

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.vm.BaseViewModel

/**
 * @author
 */
abstract class BaseMVVMActivity<V : ViewBinding, VM : BaseViewModel<*>> : BaseActivity<V>() {

    lateinit var vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        vm = initViewModel()
        super.onCreate(savedInstanceState)
    }


    abstract fun initViewModel(): VM
}