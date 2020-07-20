package com.cya.frame.demo.ext

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


fun Fragment.getNavController() = findNavController()

fun Fragment.nav(@IdRes resId: Int) {
    getNavController().navigate(resId)
}

fun Fragment.finish() {
    getNavController().navigateUp()
}