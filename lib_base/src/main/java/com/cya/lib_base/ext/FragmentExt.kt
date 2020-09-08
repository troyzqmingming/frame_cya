package com.cya.lib_base.ext

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import com.cya.frame.navigation.NavHostFragment


fun Fragment.getNavController() = NavHostFragment.findNavController(this)

fun Fragment.nav(@IdRes resId: Int) {
    getNavController().navigate(resId)
}

fun Fragment.nav(navDirections: NavDirections) {
    getNavController().navigate(navDirections)
}

fun Fragment.nav(navDirections: NavDirections, ext: Navigator.Extras) {
    getNavController().navigate(navDirections, ext)
}

fun Fragment.finish() {
    getNavController().navigateUp()
}