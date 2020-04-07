package com.cya.frame.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.vm.BaseViewModel

abstract class BaseMVVMFragment<V : ViewBinding, VM : BaseViewModel<V, *>> : Fragment() {


    lateinit var vm: VM
    lateinit var binding: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm = initViewModel()
        initView()
        initData()
        startObserve()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initViewModel(): VM
    abstract fun getViewBinding(): V
    abstract fun startObserve()
    abstract fun initView()
    abstract fun initData()
}