package com.tuanmanh.inmo.core.data.repository

import com.tuanmanh.inmo.core.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {
    suspend fun insertHabit(habit: Habit)
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(habitId: Long)
    fun getAllHabits(): Flow<List<Habit>>
    suspend fun getHabitById(id: Long): Habit?
}