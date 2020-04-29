package com.cya.frame.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cya.frame.view.ToastView

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    activity?.let {
        ToastView.show(it, msg, duration)
    }
}

fun <A : Activity> Fragment.startActivity(
    mClass: Class<A>,
    action: String? = null,
    bundle: Bundle? = null
) {
    activity?.let {
        val intent = Intent(it, mClass)
        action?.let { action ->
            intent.action = action
        }
        bundle?.let { bundle ->
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
}