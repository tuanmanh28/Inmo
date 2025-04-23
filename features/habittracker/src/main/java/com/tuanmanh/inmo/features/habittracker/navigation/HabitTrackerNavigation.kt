package com.tuanmanh.inmo.features.habittracker.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tuanmanh.inmo.features.habittracker.HabitTrackerRoute
import com.tuanmanh.inmo.features.habittracker.screens.HabitListScreen

fun NavController.navigateToHabitTracker() {
    navigate(HabitTrackerRoute.Tracker.route) {
        launchSingleTop = true
    }
}

fun NavController.navigateToHabitList() {
    navigate(HabitTrackerRoute.List.route) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.habitTrackerScreen(
    onNavigateToHabitList: () -> Unit,
    onNavigateToHabitTracker: () -> Unit
) {
    composable(route = HabitTrackerRoute.Tracker.route) {
        HabitTrackerRoute(
            onNavigateToHabitList = onNavigateToHabitList
        )
    }

    composable(route = HabitTrackerRoute.List.route) {
        HabitListScreen(
            onNavigateToHabitTracker = onNavigateToHabitTracker
        )
    }
}