package com.cya.frame.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.vm.BaseViewModel

/**
 * @author
 */
abstract class BaseMVVMActivity<V : ViewBinding, VM : BaseViewModel<V, *>> : AppCompatActivity() {

    lateinit var vm: VM
    lateinit var binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContentView()
        binding = getViewBinding()
        vm = initViewModel()
        setContentView(binding.root)
        startObserve()
        initView()
        initData()
    }

    open fun beforeSetContentView() {}

    abstract fun initViewModel(): VM
    abstract fun getViewBinding(): V
    abstract fun startObserve()
    abstract fun initView()
    abstract fun initData()
}