package com.cya.frame.demo.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import com.cya.frame.demo.R
import com.cya.frame.demo.databinding.ViewToolbarBinding
import com.cya.frame.ext.clickNoRepeat
import com.cya.frame.ext.dp2Px
import com.cya.frame.ext.px2Dp
import com.cya.frame.ext.visible

class DemoToolbar : Toolbar {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding = ViewToolbarBinding.inflate(LayoutInflater.from(context))

    init {
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(binding.root, layoutParams)
    }

    fun setTitleText(text: String? = "") {
        binding.tvToolbarTitle.text = text
    }

    fun setCloseClickListener(click: () -> Unit) {
        binding.ivToolbarClose.apply {
            visible()
            binding.guideline.setGuidelineEnd(context.run {
                resources.getDimensionPixelOffset(R.dimen.dp_100)
            })
            clickNoRepeat {
                click.invoke()
            }
        }
    }
}