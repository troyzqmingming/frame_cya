package com.cya.frame.demo.base

import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.frame.demo.R
import com.cya.frame.demo.base.vm.DemoBaseViewModel
import com.cya.frame.demo.ext.finish
import com.cya.frame.ext.clickNoRepeat

abstract class DemoBaseMVVMFragment<V : ViewBinding, VM : DemoBaseViewModel<*>> :
    BaseMVVMFragment<V, VM>() {

    override fun initView() {
        binding.root.findViewById<ImageView>(R.id.iv_toolbar_nav)?.let {
            it.clickNoRepeat {
                finish()
            }
        }
    }

}