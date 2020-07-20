package com.cya.frame.demo.base

import android.app.ProgressDialog
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.holder.Loading
import com.cya.frame.base.holder.State
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.frame.base.vm.BaseViewModel

abstract class DemoBaseMVVMFragment<V : ViewBinding, VM : BaseViewModel<V, *>> :
    BaseMVVMFragment<V, VM>() {
    private var progressDialog: ProgressDialog? = null
    fun showCommonProgress() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(activity)
        progressDialog?.show()
    }

    fun dismissCommonProgress() {
        progressDialog?.dismiss()
    }

    open fun showLoading() {}
    open fun hideLoading() {}

    override fun startObserve() {
        vm.getObservable(Loading::class.java).observe(this, Observer {
            when (it.state) {
                State.LOADING_SHOW -> showLoading()
                State.LOADING_HIDE -> hideLoading()
            }
        })
    }
}