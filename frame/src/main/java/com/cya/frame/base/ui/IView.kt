package com.cya.frame.base.ui

import androidx.viewbinding.ViewBinding

interface IView<V : ViewBinding> {

    fun getViewBinding(): V

    fun setObserve()

    fun initView()

    fun initData()
}