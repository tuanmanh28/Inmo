package com.tuanmanh.inmo.features.habittracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuanmanh.inmo.core.data.repository.HabitsRepository
import com.tuanmanh.inmo.core.model.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HabitTrackerViewModel @Inject constructor(
    private val habitsRepository: HabitsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HabitTrackerUiState())
    val uiState: StateFlow<HabitTrackerUiState> = _uiState.asStateFlow()

    init {
        loadHabits()
    }

    private fun loadHabits() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                habitsRepository.getHabits().collect { habits ->
                    _uiState.update { it.copy(habits = habits, isLoading = false) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun addHabit(name: String) {
        viewModelScope.launch {
            try {
                val habit = Habit(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    isCompletedToday = false
                )
                habitsRepository.addHabit(habit)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun toggleHabit(id: String) {
        viewModelScope.launch {
            try {
                habitsRepository.toggleHabit(id)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            try {
                habitsRepository.deleteHabit(habit)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
}