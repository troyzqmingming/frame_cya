package com.cya.frame.demo.example

import android.widget.FrameLayout
import android.widget.ImageView
import com.cya.frame.base.ui.BaseActivity
import com.cya.frame.demo.R
import com.cya.frame.demo.databinding.ActivityExampleBinding
import com.cya.frame.ext.dp2Px
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes

class ExampleActivity : BaseActivity<ActivityExampleBinding>() {
    override fun getViewBinding(): ActivityExampleBinding {
        return ActivityExampleBinding.inflate(layoutInflater)
    }

    override fun initData() {
    }

    override fun initView() {
        mutableListOf<Int>().apply {
            for (index in 0 until 4) {
                add((index % 2 == 0).yes {
                    R.mipmap.ic_launcher_round2
                }.otherwise { R.mipmap.ic_launcher_round })
            }
            var left = dp2Px((size - 1) * 40)
            forEach { index ->
                val imageView = ImageView(this@ExampleActivity)
                val layoutParams = FrameLayout.LayoutParams(dp2Px(50), dp2Px(50))
                layoutParams.setMargins(left, 0, 0, 0)
                imageView.setImageResource(index)
                this@ExampleActivity.addContentView(imageView, layoutParams)
                left -= dp2Px(40)
            }
        }
    }

}