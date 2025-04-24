package com.tuanmanh.inmo.features.habittracker.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.tuanmanh.inmo.features.habittracker.HabitTrackerRoute

@Serializable
data object HabitTrackerRoute


fun NavController.navigateToHabitTracker(
    navOptions: NavOptions,
) {
    navigate(route = HabitTrackerRoute, navOptions)
}

fun NavGraphBuilder.habitTrackerScreen(){
    composable<HabitTrackerRoute> {
        HabitTrackerRoute()
    }
}
