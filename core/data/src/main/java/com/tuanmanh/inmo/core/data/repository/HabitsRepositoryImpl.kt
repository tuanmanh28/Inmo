package com.tuanmanh.inmo.core.data.repository

import com.tuanmanh.inmo.core.data.model.asEntity
import com.tuanmanh.inmo.core.database.dao.HabitDao
import com.tuanmanh.inmo.core.database.model.HabitEntity
import com.tuanmanh.inmo.core.database.model.asExternalModel
import com.tuanmanh.inmo.core.model.Habit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HabitsRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao
) : HabitsRepository{
    override suspend fun addHabit(habit: Habit) {
        habitDao.insertHabit(habit.asEntity())
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit.asEntity())
    }

    override suspend fun toggleHabit(id: String) {
        habitDao.toggleHabit(id)
    }

    override fun getHabits(): Flow<List<Habit>> =
        habitDao.getHabitEntities()
            .map { it.map(HabitEntity::asExternalModel) }

    override fun getHabit(id: String): Flow<Habit> =
        habitDao.getHabitEntity(id).map { it.asExternalModel() }
}