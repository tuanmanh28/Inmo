package com.tuanmanh.inmo.core.data.repository

import com.tuanmanh.inmo.core.database.dao.HabitDao
import com.tuanmanh.inmo.core.database.model.HabitEntity
import com.tuanmanh.inmo.core.model.Habit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class HabitsRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao
) : HabitsRepository {

    override fun getAllHabits(): Flow<List<Habit>> =
        habitDao.getAllHabits().map { entities -> entities.map { it.toModel() } }

    override suspend fun toggleHabit(habitId: Long) {
        val entity = habitDao.getHabitById(habitId) ?: return
        val today = LocalDate.now()
        val isCompleted = !entity.isCompleted
        val streak = if (isCompleted) {
            if (entity.lastCompletedDate == today.minusDays(1)) {
                entity.streak + 1
            } else if (entity.lastCompletedDate != today) {
                1
            } else {
                entity.streak
            }
        } else {
            entity.streak
        }

        habitDao.updateHabit(
            entity.copy(
                isCompleted = isCompleted,
                streak = streak,
                lastCompletedDate = if (isCompleted) today else entity.lastCompletedDate
            )
        )
    }

    override suspend fun deleteHabit(habitId: Long) {
        habitDao.deleteHabit(habitId)
    }

    override suspend fun addHabit(name: String) {
        val entity = HabitEntity(
            name = name,
            isCompleted = false,
            streak = 0,
            lastCompletedDate = null
        )
        habitDao.insertHabit(entity)
    }

    override suspend fun getHabitById(habitId: Long): Habit? =
        habitDao.getHabitById(habitId)?.toModel()
}