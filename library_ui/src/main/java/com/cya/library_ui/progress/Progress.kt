package com.cya.library_ui.progress

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.FrameLayout
import com.cya.frame.ext.screenWidth
import com.cya.frame.ext.yes
import com.cya.library_ui.databinding.DialogProgressBinding

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

class ProgressUtil {

    companion object {
        private var dialog: Progress? = null

        fun show(context: Context, msg: String?, isCancelable: Boolean = true): Progress? {
            dialog = Progress(context).setMsg(msg).isCancelable(isCancelable)
            dialog?.show(context as Activity)
            return dialog
        }

        fun dismiss(context: Context) {
            dialog?.dismiss(context as Activity)
            dialog = null
        }
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