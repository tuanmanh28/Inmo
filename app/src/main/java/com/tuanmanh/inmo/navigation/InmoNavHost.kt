package com.tuanmanh.inmo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.tuanmanh.inmo.features.dashboard.navigation.DashboardRoute
import com.tuanmanh.inmo.features.dashboard.navigation.dashboardScreen
import com.tuanmanh.inmo.features.habittracker.navigation.habitTrackerScreen
import com.tuanmanh.inmo.features.nutrition.navigation.nutritionScreen
import com.tuanmanh.inmo.features.profile.navigation.profileScreen
import com.tuanmanh.inmo.features.workout.navigation.workoutScreen
import com.tuanmanh.inmo.ui.InmoAppState


@Composable
fun InmoNavHost(
    appState: InmoAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController, startDestination = DashboardRoute, modifier = modifier
    ) {
        dashboardScreen()
        habitTrackerScreen(navController)
        nutritionScreen()
        profileScreen()
        workoutScreen()
    }
}