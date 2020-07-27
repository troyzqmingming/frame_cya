package com.cya.frame.base.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.vm.BaseViewModel
import com.cya.frame.base.vm.HIDE
import com.cya.frame.base.vm.SHOW

abstract class BaseMVVMFragment<V : ViewBinding, VM : BaseViewModel<*>> : BaseFragment<V>() {


    lateinit var vm: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm = initViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initViewModel(): VM

    override fun startObserve() {
        vm.loadingLiveData.observe(this, Observer {
            when (it) {
                SHOW -> showLoading()
                HIDE -> hideLoading()
            }
        })
    }

    open fun showLoading() {}
    open fun hideLoading() {}

}