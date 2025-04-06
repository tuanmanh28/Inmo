package com.tuanmanh.inmo.core.data.model

import com.tuanmanh.inmo.core.database.model.HabitEntity
import com.tuanmanh.inmo.core.model.Habit
import com.tuanmanh.inmo.core.network.model.NetworkHabit

fun NetworkHabit.asEntity() = HabitEntity(
    id = id,
    name = name,
    isCompletedToday = isCompletedToday
)

fun Habit.asEntity() = HabitEntity(
    id = id,
    name = name,
    isCompletedToday = isCompletedToday
)