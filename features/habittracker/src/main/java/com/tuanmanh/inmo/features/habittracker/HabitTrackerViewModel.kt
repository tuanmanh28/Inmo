package com.tuanmanh.inmo.features.habittracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuanmanh.inmo.core.data.repository.HabitRecordRepository
import com.tuanmanh.inmo.core.data.repository.HabitsRepository
import com.tuanmanh.inmo.core.data.repository.TimeRepository
import com.tuanmanh.inmo.core.model.Habit
import com.tuanmanh.inmo.core.model.HabitRecord
import com.tuanmanh.inmo.core.model.HabitStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HabitTrackerViewModel @Inject constructor(
    private val habitsRepository: HabitsRepository,
    private val timeRepository: TimeRepository,
    private val habitRecordRepository: HabitRecordRepository
) : ViewModel() {
    val currentDate = timeRepository.currentDate.stateIn(
        viewModelScope, initialValue = LocalDate.now(), started = SharingStarted.Lazily
    )
    val selectedDate = timeRepository.selectedDate.stateIn(
        viewModelScope, initialValue = LocalDate.now(), started = SharingStarted.Lazily
    )
    private var loadJob: Job?= null

    private val habits = habitsRepository.getAllHabits()
        .stateIn(viewModelScope, initialValue = emptyList(), started = SharingStarted.Eagerly)

    private val _uiState: MutableStateFlow<HabitTrackerUiState> =
        MutableStateFlow(HabitTrackerUiState.Empty)
    var uiState: StateFlow<HabitTrackerUiState> = _uiState.asStateFlow()

    init {
        loadHabits(currentDate.value)
    }

    fun onDateSelected(date: LocalDate) {
        timeRepository.onDateSelected(date)
        loadHabits(date)
    }

    private fun updateHabitRecordsForDay(date: LocalDate) {
        viewModelScope.launch {
            Log.d(TAG, "updateHabitRecordsForDay: ${habits.value}")
            habits.value.forEach { habit ->
                if (habit.startDate?.isAfter(date) == true || habit.endDate?.isBefore(date) == true) {
                    return@forEach
                }
                val habitRecord = habitRecordRepository.getHabitRecord(habit.id, date)
                if (habitRecord == null) {
                    habitRecordRepository.insertHabitRecord(
                        HabitRecord(
                            habitId = habit.id, date = date, status = HabitStatus.NOT_COMPLETED
                        )
                    )
                }
            }
        }
    }

    private fun loadHabits(localDate: LocalDate) {
        Log.d(TAG, "loadHabits: $localDate")
        updateHabitRecordsForDay(localDate)
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            try {
                _uiState.value = HabitTrackerUiState.Loading
                habitRecordRepository.getHabitRecordsForDay(localDate).collect { habitRecords ->
                    if (habitRecords.isEmpty()) {
                        Log.d(TAG, "loadHabits: Empty")
                        _uiState.value = HabitTrackerUiState.Empty
                    } else {
                        val habits = mutableMapOf<Habit, HabitStatus>()
                        habitRecords.forEach { habitRecord ->
                            val habit = habitsRepository.getHabitById(habitRecord.habitId)
                            habit?.let {
                                habits[it] = habitRecord.status
                            }
                        }
                        _uiState.value = HabitTrackerUiState.Success(habits)
                    }
                }
            } catch (e: Exception) {
//                _uiState.value = HabitTrackerUiState.LoadFailed(e.message.toString())
            }
        }
    }

    fun addHabit(habit: Habit) {
        Log.d(TAG, "addHabit: $habit")
        viewModelScope.launch {
            try {
                habitsRepository.insertHabit(habit)
                loadHabits(selectedDate.value)
            } catch (e: Exception) {
//                _uiState.value = HabitTrackerUiState.LoadFailed(e.message.toString())
            }
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            try {
                habitsRepository.updateHabit(habit)
            } catch (e: Exception) {
//                _uiState.value = HabitTrackerUiState.LoadFailed(e.message.toString())
            }
        }
    }

    fun toggleHabit(id: Long) {
        Log.d(TAG, "toggleHabit: $id")
        viewModelScope.launch {
            try {
                val habitRecord = habitRecordRepository.getHabitRecord(id, selectedDate.value)
                if (habitRecord == null) {
                    habitRecordRepository.insertHabitRecord(
                        HabitRecord(
                            habitId = id, date = selectedDate.value, status = HabitStatus.COMPLETED
                        )
                    )
                } else {
                    when (habitRecord.status) {
                        HabitStatus.COMPLETED -> {
                            habitRecordRepository.updateHabitRecord(
                                habitRecord.copy(
                                    status = HabitStatus.NOT_COMPLETED
                                )
                            )
                        }

                        HabitStatus.NOT_COMPLETED -> {
                            habitRecordRepository.updateHabitRecord(
                                habitRecord.copy(
                                    status = HabitStatus.COMPLETED
                                )
                            )
                        }

                        HabitStatus.SKIPPED -> {}
                    }
                }

            } catch (e: Exception) {
//                _uiState.value = HabitTrackerUiState.LoadFailed(e.message.toString())
            }
        }
    }

    fun skipHabit(id: Long) {
        viewModelScope.launch {
            try {
                val habitRecord = habitRecordRepository.getHabitRecord(id, selectedDate.value)
                if (habitRecord == null) {
                    habitRecordRepository.insertHabitRecord(
                        HabitRecord(
                            habitId = id, date = selectedDate.value, status = HabitStatus.SKIPPED
                        )
                    )
                } else {
                    habitRecordRepository.updateHabitRecord(
                        habitRecord.copy(
                            status = HabitStatus.SKIPPED
                        )
                    )
                }

            } catch (e: Exception) {
//                _uiState.value = HabitTrackerUiState.LoadFailed(e.message.toString())
            }
        }
    }

    fun deleteHabit(habitId: Long) {
        viewModelScope.launch {
            try {
                habitsRepository.deleteHabit(habitId)
                loadHabits(selectedDate.value)
            } catch (e: Exception) {
//                _uiState.value = HabitTrackerUiState.LoadFailed(e.message.toString())
            }
        }
    }

    companion object {
        private const val TAG = "HabitTrackerViewModel"
    }
}