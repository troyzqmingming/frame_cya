package com.cya.frame.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.vm.BaseRepository
import com.cya.frame.base.vm.BaseViewModel

fun <V : ViewBinding, R : BaseRepository> BaseViewModel<V, R>.getActivity(): Activity? {
    val activity = binding.root.context
    return if (activity is Activity) {
        return activity
    } else {
        null
    }
}

fun <V : ViewBinding, R : BaseRepository, A : Activity>
        BaseViewModel<V, R>.startActivity(
    mClass: Class<A>,
    action: String? = null,
    bundle: Bundle? = null
) {
    val intent = Intent(binding.root.context, mClass)
    action?.let {
        intent.action = it
    }
    bundle?.let {
        intent.putExtras(it)
    }
    binding.root.context?.startActivity(intent)
}

fun <V : ViewBinding, R : BaseRepository>
        BaseViewModel<V, R>.finishActivity(
) {
    getActivity()?.let {
        if (!it.isFinishing) {
            it.finish()
        }
    }
}