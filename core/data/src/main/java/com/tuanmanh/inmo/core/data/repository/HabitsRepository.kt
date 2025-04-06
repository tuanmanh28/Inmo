package com.tuanmanh.inmo.core.data.repository

import com.tuanmanh.inmo.core.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {
    suspend fun addHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    suspend fun toggleHabit(id: String)
    fun getHabits(): Flow<List<Habit>>
    fun getHabit(id: String): Flow<Habit>
}