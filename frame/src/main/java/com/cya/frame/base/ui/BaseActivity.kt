package com.cya.frame.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {

    lateinit var binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContentView()
        binding = getViewBinding()
        setContentView(binding.root)
        startObserve()
        initView()
        initData()
    }

    abstract fun getViewBinding(): V
    open fun startObserve(){}
    abstract fun initView()
    abstract fun initData()

    open fun beforeSetContentView() {}
}