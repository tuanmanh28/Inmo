package com.tuanmanh.inmo.features.habittracker.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import com.tuanmanh.inmo.features.habittracker.HabitTrackerRoute
import com.tuanmanh.inmo.features.habittracker.HabitUpdateRoute

@Serializable
data object HabitTrackerRoute


@Serializable
data class HabitUpdateRoute(val habitId: Long)

@Serializable
data object HabitCreateRoute


fun NavController.navigateToHabitTracker(
    navOptions: NavOptions?,

) {
    navigate(route = HabitTrackerRoute, navOptions)
}
fun NavController.navigateToUpdateHabit(
    habitId: Long?,
    navOptions: NavOptions? = null,
) {
    habitId?.let {
        navigate(HabitUpdateRoute(it), navOptions)
    } ?: run {
        navigate(HabitCreateRoute, navOptions)
    }
}

fun NavGraphBuilder.habitTrackerScreen(
    navController: NavController
){
    composable<HabitTrackerRoute> {
        HabitTrackerRoute(
            navigateToUpdateScreen = { habitId ->
                navController.navigateToUpdateHabit(habitId)
            }
        )
    }
    composable<HabitUpdateRoute> { backStackEntry ->
        val route = backStackEntry.toRoute<HabitUpdateRoute>()
        HabitUpdateRoute(
            habitId = route.habitId,
            navigateToHabitTracker = {
                navController.navigateToHabitTracker(navOptions = null)
            },
        )
    }
    composable<HabitCreateRoute> {
        HabitUpdateRoute(
            navigateToHabitTracker = {
                navController.navigateToHabitTracker(navOptions = null)
            },
        )
    }
}
