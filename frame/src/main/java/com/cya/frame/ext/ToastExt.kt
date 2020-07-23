package com.cya.frame.ext

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.cya.frame.CyaSDK
import com.cya.frame.view.ToastView

private fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    ToastView.show(this, getString(id), duration)
}

fun Activity.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    ToastView.show(this, msg, duration)
}

fun Activity.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    baseContext.toast(id, duration)
}

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    activity?.let {
        ToastView.show(it, msg, duration)
    }
}

fun Fragment.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    activity?.let {
        it.toast(id, duration)
    }
}

fun Any.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    ToastView.show(CyaSDK.getContext(), msg, duration)
}

fun Any.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    CyaSDK.getContext().toast(id, duration)
}