package com.cya.frame.demo.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.FrameLayout
import com.cya.frame.demo.databinding.DialogProgressBinding
import com.cya.frame.ext.screenWidth
import com.cya.frame.ext.yes

fun Dialog.show(activity: Activity) {
    (!activity.isFinishing && !isShowing).yes {
        show()
    }
}

fun Dialog.dismiss(activity: Activity) {
    (!activity.isFinishing && isShowing).yes {
        dismiss()
    }
}

class Progress(context: Context) : Dialog(context) {

    private val binding = DialogProgressBinding.inflate(layoutInflater)

    init {
        setContentView(binding.root)
        FrameLayout.LayoutParams(
            (context.screenWidth * 0.8).toInt(),
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            binding.llRoot.layoutParams = this
        }

    }

    fun setMsg(msg: String?): Progress {
        binding.tvMsg.text = msg
        return this
    }

    fun isCancelable(isCancelable: Boolean): Progress {
        setCancelable(isCancelable)
        return this
    }

}