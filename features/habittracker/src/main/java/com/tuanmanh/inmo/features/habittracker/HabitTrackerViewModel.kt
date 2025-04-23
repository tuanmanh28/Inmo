package com.tuanmanh.inmo.features.habittracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuanmanh.inmo.core.data.repository.HabitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitTrackerViewModel @Inject constructor(
    private val habitsRepository: HabitsRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HabitTrackerUiState> = MutableStateFlow(HabitTrackerUiState.Empty)
    var uiState: StateFlow<HabitTrackerUiState> = _uiState.asStateFlow()

    init {
        loadHabits()
    }

    private fun loadHabits() {
        viewModelScope.launch {
            try {
                _uiState.value = HabitTrackerUiState.Loading
                habitsRepository.getAllHabits().collect { habits ->
                    if (habits.isEmpty()){
                        _uiState.value = HabitTrackerUiState.Empty
                    }
                    else{
                        _uiState.value = HabitTrackerUiState.Success(habits)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = HabitTrackerUiState.LoadFailed(e.message.toString())
            }
        }
    }

    fun addHabit(name: String) {
        viewModelScope.launch {
            try {
                habitsRepository.addHabit(name)
            } catch (e: Exception) {
                _uiState.value = HabitTrackerUiState.LoadFailed(e.message.toString())
            }
        }
    }

    fun toggleHabit(id: Long) {
        viewModelScope.launch {
            try {
                habitsRepository.toggleHabit(id)
            } catch (e: Exception) {
                _uiState.value = HabitTrackerUiState.LoadFailed(e.message.toString())
            }
        }
    }

    fun deleteHabit(habitId: Long) {
        viewModelScope.launch {
            try {
                habitsRepository.deleteHabit(habitId)
            } catch (e: Exception) {
                _uiState.value = HabitTrackerUiState.LoadFailed(e.message.toString())
            }
        }
    }
}