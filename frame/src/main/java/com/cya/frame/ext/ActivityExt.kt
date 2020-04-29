package com.cya.frame.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.cya.frame.view.ToastView

fun Activity.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    ToastView.show(this, msg, duration)
}


fun <A : Activity>
        Activity.startActivity(
    mClass: Class<A>,
    action: String? = null,
    bundle: Bundle? = null
) {
    val intent = Intent(this, mClass)
    action?.let {
        intent.action = it
    }
    bundle?.let {
        intent.putExtras(it)
    }
    this.startActivity(intent)
}