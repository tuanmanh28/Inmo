package com.tuanmanh.inmo.features.habittracker.navigation

sealed class HabitTrackerRoute(val route: String) {
    object Tracker : HabitTrackerRoute("habit_tracker")
    object List : HabitTrackerRoute("habit_tracker/list")
} 