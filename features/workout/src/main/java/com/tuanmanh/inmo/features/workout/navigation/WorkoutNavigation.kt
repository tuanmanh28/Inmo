package com.tuanmanh.inmo.features.workout.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.tuanmanh.inmo.features.workout.WorkoutRoute

@Serializable
data object WorkoutRoute


fun NavController.navigateToWorkout(
    navOptions: NavOptions,
) {
    navigate(route = WorkoutRoute, navOptions)
}

fun NavGraphBuilder.workoutScreen(){
    composable<WorkoutRoute> {
        WorkoutRoute()
    }
}
