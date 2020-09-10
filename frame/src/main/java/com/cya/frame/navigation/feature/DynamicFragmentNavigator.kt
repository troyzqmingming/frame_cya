package com.cya.frame.navigation.feature

/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.core.content.withStyledAttributes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.NavigatorProvider
import androidx.navigation.dynamicfeatures.DynamicExtras
import androidx.navigation.dynamicfeatures.DynamicInstallManager
import com.cya.frame.R
import com.cya.frame.navigation.FragmentNavigator

/**
 * The [Navigator] that enables navigating to destinations within dynamic feature modules.
 */
@Navigator.Name("fragment")
class DynamicFragmentNavigator(
    context: Context,
    manager: FragmentManager,
    containerId: Int,
    private val installManager: DynamicInstallManager
) : FragmentNavigator(context, manager, containerId) {

    override fun createDestination() = Destination(this)

    override fun navigate(
        destination: FragmentNavigator.Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        val extras = navigatorExtras as? DynamicExtras
        if (destination is Destination) {
            val moduleName = destination.moduleName
            if (moduleName != null && installManager.needsInstall(moduleName)) {
                return installManager.performInstall(destination, args, extras, moduleName)
            }
        }
        return super.navigate(
            destination,
            args,
            navOptions,
            if (extras != null) extras.destinationExtras else navigatorExtras
        )
    }

    /**
     * Destination for dynamic feature navigator.
     */
    class Destination : FragmentNavigator.Destination {
        var moduleName: String? = null

        @Suppress("unused")
        constructor(navigatorProvider: NavigatorProvider) : super(navigatorProvider)

        constructor(
            fragmentNavigator: Navigator<out FragmentNavigator.Destination>
        ) : super(fragmentNavigator)

        override fun onInflate(context: Context, attrs: AttributeSet) {
            super.onInflate(context, attrs)
            context.withStyledAttributes(attrs, R.styleable.DynamicFragmentNavigator) {
                moduleName = getString(R.styleable.DynamicFragmentNavigator_moduleName)
            }
        }
    }
}
