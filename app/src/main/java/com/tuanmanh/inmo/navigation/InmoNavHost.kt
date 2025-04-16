package com.tuanmanh.inmo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.tuanmanh.inmo.features.habittracker.navigation.HabitTrackerRoute
import com.tuanmanh.inmo.features.habittracker.navigation.habitTrackerScreen

@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController()
): InmoAppState {
    return remember(
        navHostController
    ) {
        InmoAppState(
            navHostController = navHostController
        )
    }

}

@Stable
class InmoAppState(
    val navHostController: NavHostController
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry =
                navHostController.currentBackStackEntryFlow.collectAsState(initial = null)

            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }
}

@Composable
fun InmoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HabitTrackerRoute.ROUTE,
        modifier = modifier
    ) {
        habitTrackerScreen()
    }
}