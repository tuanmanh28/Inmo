package com.tuanmanh.inmo.core.data.model

import com.tuanmanh.inmo.core.database.model.HabitRecordEntity
import com.tuanmanh.inmo.core.model.HabitRecord
import com.tuanmanh.inmo.core.network.model.NetWorkHabitRecord

fun HabitRecord.asEntity() = HabitRecordEntity(
    id = id,
    habitId = habitId,
    date = date,
    status = status
)

fun NetWorkHabitRecord.asEntity() = HabitRecordEntity(
    id = id,
    habitId = habitId,
    date = date,
    status = status
)