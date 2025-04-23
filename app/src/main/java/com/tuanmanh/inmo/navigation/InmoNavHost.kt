package com.tuanmanh.inmo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.tuanmanh.inmo.features.habittracker.navigation.HabitTrackerRoute
import com.tuanmanh.inmo.features.habittracker.navigation.habitTrackerScreen
import com.tuanmanh.inmo.features.habittracker.navigation.navigateToHabitList
import com.tuanmanh.inmo.features.habittracker.navigation.navigateToHabitTracker

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        InmoAppState(navController)
    }

@Stable
class InmoAppState(
    val navController: NavHostController
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry =
                navController.currentBackStackEntryFlow.collectAsState(initial = null)

            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    fun navigateToHabitTracker() {
        navController.navigateToHabitTracker()
    }

    fun navigateToHabitList() {
        navController.navigateToHabitList()
    }
}

@Composable
fun InmoNavHost(
    appState: InmoAppState = rememberAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = HabitTrackerRoute.Tracker.route
    ) {
        habitTrackerScreen(
            onNavigateToHabitList = appState::navigateToHabitList,
            onNavigateToHabitTracker = appState::navigateToHabitTracker
        )
    }
}