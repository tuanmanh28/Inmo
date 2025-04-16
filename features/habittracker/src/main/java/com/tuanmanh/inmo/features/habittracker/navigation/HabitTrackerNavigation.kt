package com.tuanmanh.inmo.features.habittracker.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tuanmanh.inmo.features.habittracker.HabitTrackerScreen

fun NavController.navigateToHabitTracker() {
    navigate(HabitTrackerRoute.ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.habitTrackerScreen() {
    composable(route = HabitTrackerRoute.ROUTE) {
        HabitTrackerScreen()
    }
}