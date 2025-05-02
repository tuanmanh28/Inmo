package com.tuanmanh.inmo.core.data.repository.impl

import android.util.Log
import com.tuanmanh.inmo.core.data.model.asEntity
import com.tuanmanh.inmo.core.data.repository.HabitsRepository
import com.tuanmanh.inmo.core.database.dao.HabitDao
import com.tuanmanh.inmo.core.model.Habit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HabitsRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao
) : HabitsRepository {

    override fun getAllHabits(): Flow<List<Habit>> =
        habitDao.getAllHabits().map { entities -> entities.map { it.toModel() } }

    override suspend fun insertHabit(habit: Habit) {
        Log.d(TAG, "insertHabit: $habit")
        habitDao.insertHabit(habit.asEntity())
    }
    override suspend fun updateHabit(habit: Habit) {
        Log.d(TAG, "updateHabit: $habit")
        habitDao.updateHabit(habit.asEntity())
    }
    override suspend fun deleteHabit(habitId: Long) {
        Log.d(TAG, "deleteHabit: $habitId")
        habitDao.deleteHabit(habitId)
    }

    override suspend fun getHabitById(id: Long): Habit? {
        val habit = habitDao.getHabitById(id)?.toModel()
        Log.d(TAG, "getHabitById: $habit")
        return habit
    }

    companion object{
        const val TAG = "HabitsRepository"
    }
}