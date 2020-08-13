package com.cya.frame.demo.ext

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.cya.frame.demo.R
import com.cya.frame.navigation.NavHostFragment


fun Fragment.getNavController() = NavHostFragment.findNavController(this)

fun Fragment.nav(@IdRes resId: Int) {
    getNavController().navigate(resId)
}

fun Fragment.nav(navDirections: NavDirections) {
    getNavController().navigate(navDirections)
}

fun Fragment.back2Main(inclusive: Boolean = false) {
    getNavController().popBackStack(R.id.mainFragment, inclusive)
}

fun Fragment.finish() {
    getNavController().navigateUp()
}