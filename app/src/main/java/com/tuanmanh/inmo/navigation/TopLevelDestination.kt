package com.tuanmanh.inmo.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.tuanmanh.inmo.core.designsystem.icon.InmoIcons
import com.tuanmanh.inmo.features.dashboard.navigation.DashboardRoute
import com.tuanmanh.inmo.features.habittracker.navigation.HabitTrackerRoute
import com.tuanmanh.inmo.features.nutrition.navigation.NutritionRoute
import com.tuanmanh.inmo.features.profile.navigation.ProfileRoute
import com.tuanmanh.inmo.features.workout.navigation.WorkoutRoute
import kotlin.reflect.KClass
import com.tuanmanh.inmo.features.dashboard.R as dashboardR
import com.tuanmanh.inmo.features.habittracker.R as habittrackerR
import com.tuanmanh.inmo.features.nutrition.R as nutritionR
import com.tuanmanh.inmo.features.profile.R as profileR
import com.tuanmanh.inmo.features.workout.R as workoutR

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
) {
    DASHBOARD(
        selectedIcon = InmoIcons.Home,
        unselectedIcon = InmoIcons.HomeBorder,
        iconTextId = dashboardR.string.feature_dashboard_title,
        titleTextId = dashboardR.string.feature_dashboard_title,
        route = DashboardRoute::class
    ),
    HABITS(
        selectedIcon = InmoIcons.Habits,
        unselectedIcon = InmoIcons.HabitsBorder,
        iconTextId = habittrackerR.string.feature_habittracker_title,
        titleTextId = habittrackerR.string.feature_habittracker_title,
        route = HabitTrackerRoute::class
    ),
    NUTRITION(
        selectedIcon = InmoIcons.Nutrition,
        unselectedIcon = InmoIcons.NutritionBorder,
        iconTextId = nutritionR.string.feature_nutrition_title,
        titleTextId = nutritionR.string.feature_nutrition_title,
        route = NutritionRoute::class
    ),
    WORKOUT(
        selectedIcon = InmoIcons.Workout,
        unselectedIcon = InmoIcons.WorkoutBorder,
        iconTextId = workoutR.string.feature_workout_title,
        titleTextId = workoutR.string.feature_workout_title,
        route = WorkoutRoute::class
    ),
    PROFILE(
        selectedIcon = InmoIcons.Profile,
        unselectedIcon = InmoIcons.ProfileBorder,
        iconTextId = profileR.string.feature_profile_title,
        titleTextId = profileR.string.feature_profile_title,
        route = ProfileRoute::class
    )
}