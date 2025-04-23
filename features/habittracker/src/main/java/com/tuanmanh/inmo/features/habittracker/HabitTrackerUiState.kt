package com.tuanmanh.inmo.features.habittracker

import com.tuanmanh.inmo.core.model.Habit
import java.lang.Error

sealed interface HabitTrackerUiState {
    data object Loading: HabitTrackerUiState

    data object Empty : HabitTrackerUiState

    data class LoadFailed (
        val error: String
    ) : HabitTrackerUiState

    data class Success(
        val habits: List<Habit> = emptyList()
    ): HabitTrackerUiState
}