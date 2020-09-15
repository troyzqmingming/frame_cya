package com.cya.frame.base.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.IView

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity(), IView<V> {

    lateinit var binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setObserve()
        initView()
        initData()
    }

}