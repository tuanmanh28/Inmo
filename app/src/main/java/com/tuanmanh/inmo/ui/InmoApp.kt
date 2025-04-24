package com.tuanmanh.inmo.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.tuanmanh.inmo.core.designsystem.component.InmoNavigationSuiteScaffold
import com.tuanmanh.inmo.navigation.InmoNavHost
import kotlin.reflect.KClass

@Composable
fun InmoApp(
    appState: InmoAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    val currentDestination = appState.currentDestination

    InmoNavigationSuiteScaffold(
        navigationSuiteItems = {
            appState.topLevelDestinations.forEach{ destination ->
                val selected = currentDestination.isRouteInHierarchy(destination.route)
                item(
                    selected = selected,
                    onClick = { appState.navigateToTopLevelDestination(destination) },
                    icon = {
                        Icon(
                            imageVector = destination.unselectedIcon,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = destination.selectedIcon,
                            contentDescription = null,
                        )
                    },
                    label = { Text(stringResource(destination.iconTextId)) },
                    modifier =
                    Modifier
                        .testTag("InmoNavItem")
                        .then(Modifier),
                )
            }
        },
        windowAdaptiveInfo = windowAdaptiveInfo
    )
    {
        InmoNavHost(
            appState = appState,
            modifier = modifier,
        )
    }
}


private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false
