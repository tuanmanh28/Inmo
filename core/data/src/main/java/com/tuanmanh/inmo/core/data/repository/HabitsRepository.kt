package com.tuanmanh.inmo.core.data.repository

import com.tuanmanh.inmo.core.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {
    fun getAllHabits(): Flow<List<Habit>>
    suspend fun toggleHabit(habitId: Long)
    suspend fun deleteHabit(habitId: Long)
    suspend fun addHabit(name: String)
    suspend fun getHabitById(habitId: Long): Habit?
}