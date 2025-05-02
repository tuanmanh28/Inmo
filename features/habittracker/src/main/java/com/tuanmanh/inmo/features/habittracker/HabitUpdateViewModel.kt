package com.tuanmanh.inmo.features.habittracker

import android.util.Log
import androidx.lifecycle.SavedStateHandle
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
class HabitUpdateViewModel @Inject constructor(
    private val habitsRepository: HabitsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _habit = MutableStateFlow<Habit?>(null)
    val habit: StateFlow<Habit?> = _habit.asStateFlow()
    init {
        val habitId: Long? = savedStateHandle["habitId"]
        if (habitId != null) {
            getHabitById(habitId)
        }
    }

    fun getHabitById(id: Long?) {
        Log.d(TAG, "getHabitById: $id")
        viewModelScope.launch {
            id ?: return@launch
            _habit.value = habitsRepository.getHabitById(id)
        }
    }
    fun updateHabit(habit: Habit){
        viewModelScope.launch {
            habitsRepository.updateHabit(habit)
        }
    }
    fun insertHabit(habit: Habit){
        viewModelScope.launch {
            habitsRepository.insertHabit(habit)
        }
    }
    fun deleteHabit(id: Long){
        viewModelScope.launch {
            habitsRepository.deleteHabit(id)
        }
    }
    companion object{
        private const val TAG = "HabitUpdateViewModel"
    }
}