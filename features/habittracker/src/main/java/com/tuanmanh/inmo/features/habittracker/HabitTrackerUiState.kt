package com.tuanmanh.inmo.features.habittracker

import com.tuanmanh.inmo.core.model.Habit

sealed interface HabitTrackerUiState {
    data object Loading: HabitTrackerUiState

    data object EmptyQuery : HabitTrackerUiState

    data object LoadFailed : HabitTrackerUiState

    data class Success(
        val habits: List<Habit> = emptyList()
    )
}