package com.tuanmanh.inmo.features.habittracker

import com.tuanmanh.inmo.core.model.Habit

data class HabitTrackerUiState(
    val habits: List<Habit> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)