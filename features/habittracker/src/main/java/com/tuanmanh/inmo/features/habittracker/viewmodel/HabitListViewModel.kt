package com.tuanmanh.inmo.features.habittracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuanmanh.inmo.core.data.repository.HabitsRepository
import com.tuanmanh.inmo.core.model.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitListViewModel @Inject constructor(
    private val habitsRepository: HabitsRepository
) : ViewModel() {

    private val _habits = MutableStateFlow<List<Habit>>(emptyList())
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    fun loadHabits() {
        viewModelScope.launch {
            habitsRepository.getAllHabits().collect { habits ->
                _habits.value = habits
            }
        }
    }

    fun toggleHabit(habitId: Long) {
        viewModelScope.launch {
            habitsRepository.toggleHabit(habitId)
        }
    }

    fun deleteHabit(habitId: Long) {
        viewModelScope.launch {
            habitsRepository.deleteHabit(habitId)
        }
    }
} 