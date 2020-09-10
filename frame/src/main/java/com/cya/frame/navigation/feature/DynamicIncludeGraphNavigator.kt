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

package com.cya.frame.navigation.feature

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.AttributeSet
import androidx.annotation.RestrictTo
import androidx.core.content.withStyledAttributes
import androidx.navigation.*
import androidx.navigation.NavInflater.APPLICATION_ID_PLACEHOLDER
import androidx.navigation.dynamicfeatures.DynamicExtras
import androidx.navigation.dynamicfeatures.DynamicInstallManager
import androidx.navigation.dynamicfeatures.R

/**
 * Navigator for `include-dynamic`.
 *
 * Use it for navigating to NavGraphs contained within a dynamic feature module.
 */
@Navigator.Name("include-dynamic")
class DynamicIncludeGraphNavigator(
    private val context: Context,
    private val navigatorProvider: NavigatorProvider,
    private val navInflater: NavInflater,
    private val installManager: DynamicInstallManager
) : Navigator<DynamicIncludeGraphNavigator.DynamicIncludeNavGraph>() {

    /**
     * @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    val packageName: String = context.packageName

    private val createdDestinations = mutableListOf<DynamicIncludeNavGraph>()

    override fun createDestination(): DynamicIncludeNavGraph {
        return DynamicIncludeNavGraph(this).also {
            createdDestinations.add(it)
        }
    }

    /**
     * @throws Resources.NotFoundException if the [destination] does not have a valid
     * `graphResourceName` and `graphPackage`.
     * @throws IllegalStateException if the [destination] does not have a parent.
     */
    override fun navigate(
        destination: DynamicIncludeNavGraph,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination? {
        val extras = navigatorExtras as? DynamicExtras

        val moduleName = destination.moduleName

        return if (moduleName != null && installManager.needsInstall(moduleName)) {
            installManager.performInstall(destination, args, extras, moduleName)
        } else {
            val includedNav = replaceWithIncludedNav(destination)
            val navigator: Navigator<NavDestination> = navigatorProvider[includedNav.navigatorName]
            navigator.navigate(includedNav, args, navOptions, navigatorExtras)
        }
    }

    /**
     * Replace the given [destination] with the included navigation graph it references.
     *
     * @return the newly inflated included navigation graph
     */
    private fun replaceWithIncludedNav(destination: DynamicIncludeNavGraph): NavGraph {
        val graphId = context.resources.getIdentifier(
            destination.graphResourceName, "navigation",
            destination.graphPackage)
        if (graphId == 0) {
            throw Resources.NotFoundException(
                "${destination.graphPackage}:navigation/${destination.graphResourceName}")
        }
        val includedNav = navInflater.inflate(graphId)
        check(!(includedNav.id != 0 && includedNav.id != destination.id)) {
            "The included <navigation>'s id ${includedNav.displayName} is different from " +
                    "the destination id ${destination.displayName}. Either remove the " +
                    "<navigation> id or make them match."
        }
        includedNav.id = destination.id
        val outerNav = destination.parent
            ?: throw IllegalStateException(
                "The include-dynamic destination with id ${destination.displayName} " +
                        "does not have a parent. Make sure it is attached to a NavGraph."
            )
        outerNav.addDestinations(includedNav)
        // Avoid calling replaceWithIncludedNav() on the same destination more than once
        createdDestinations.remove(destination)
        return includedNav
    }

    override fun popBackStack() = true

    override fun onSaveState(): Bundle? {
        // Return a non-null Bundle to get a callback to onRestoreState
        return Bundle.EMPTY
    }

    override fun onRestoreState(savedState: Bundle) {
        super.onRestoreState(savedState)
        // replaceWithIncludedNav() can add more elements while we're iterating
        // through the list so we need to keep iterating until there's no more
        // unexpanded graphs
        while (createdDestinations.isNotEmpty()) {
            // Iterate through a copy to prevent ConcurrentModificationExceptions
            val iterator = ArrayList(createdDestinations).iterator()
            // And clear the original list so that the list only contains
            // newly inflated destinations from the replaceWithIncludedNav() calls
            // the next time our loop completes
            createdDestinations.clear()
            while (iterator.hasNext()) {
                val dynamicNavGraph = iterator.next()
                val moduleName = dynamicNavGraph.moduleName
                if (moduleName == null || !installManager.needsInstall(moduleName)) {
                    replaceWithIncludedNav(dynamicNavGraph)
                }
            }
        }
    }

    /**
     * The graph for dynamic-include.
     *
     * This class contains information to navigate to a DynamicNavGraph which is contained
     * within a dynamic feature module.
     */
    class DynamicIncludeNavGraph
    internal constructor(navGraphNavigator: Navigator<out NavDestination>) :
        NavDestination(navGraphNavigator) {

        /**
         * Resource name of the graph.
         */
        var graphResourceName: String? = null

        /**
         * The graph's package.
         */
        var graphPackage: String? = null

        /**
         * Name of the module containing the included graph, if set.
         */
        var moduleName: String? = null

        override fun onInflate(context: Context, attrs: AttributeSet) {
            super.onInflate(context, attrs)
            context.withStyledAttributes(attrs, R.styleable.DynamicIncludeGraphNavigator) {
                moduleName = getString(R.styleable.DynamicIncludeGraphNavigator_moduleName)
                require(!moduleName.isNullOrEmpty()) {
                    "`moduleName` must be set for <include-dynamic>"
                }

                graphPackage = getString(R.styleable.DynamicIncludeGraphNavigator_graphPackage)
                    .let {
                        if (it != null) {
                            require(it.isNotEmpty()) {
                                "`graphPackage` cannot be empty for <include-dynamic>. You can " +
                                        "omit the `graphPackage` attribute entirely to use the " +
                                        "default of ${context.packageName}.$moduleName."
                            }
                        }
                        getPackageOrDefault(context, it)
                    }

                graphResourceName =
                    getString(R.styleable.DynamicIncludeGraphNavigator_graphResName)
                require(!graphResourceName.isNullOrEmpty()) {
                    "`graphResName` must be set for <include-dynamic>"
                }
            }
        }

        internal fun getPackageOrDefault(
            context: Context,
            graphPackage: String?
        ): String {
            return graphPackage?.replace(
                APPLICATION_ID_PLACEHOLDER,
                context.packageName
            ) ?: "${context.packageName}.$moduleName"
        }
    }
}
