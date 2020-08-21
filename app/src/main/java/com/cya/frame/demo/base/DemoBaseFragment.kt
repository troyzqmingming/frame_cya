package com.cya.frame.demo.base

import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.BaseFragment
import com.cya.frame.demo.R
import com.cya.frame.demo.ext.finish
import com.cya.frame.ext.clickNoRepeat

abstract class DemoBaseFragment<V : ViewBinding> : BaseFragment<V>() {


    override fun initView() {
        binding.root.findViewById<ImageView>(R.id.iv_toolbar_nav)?.let {
            it.clickNoRepeat {
                finish()
            }
        }
    }
}