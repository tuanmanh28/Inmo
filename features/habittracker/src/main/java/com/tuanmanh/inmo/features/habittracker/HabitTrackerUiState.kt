package com.tuanmanh.inmo.features.habittracker

import com.tuanmanh.inmo.core.model.Habit
import com.tuanmanh.inmo.core.model.HabitStatus

sealed interface HabitTrackerUiState {
    data object Loading : HabitTrackerUiState

    data object Empty : HabitTrackerUiState

    data class LoadFailed(
        val error: String
    ) : HabitTrackerUiState

    data class Success(
        val habits: Map<Habit, HabitStatus> = emptyMap(),
    ) : HabitTrackerUiState
}