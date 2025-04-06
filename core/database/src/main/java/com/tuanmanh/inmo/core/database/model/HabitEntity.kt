package com.tuanmanh.inmo.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tuanmanh.inmo.core.model.Habit

@Entity(
    tableName = "habits"
)
data class HabitEntity(
    @PrimaryKey
    val id: String,
    val name: String,

    @ColumnInfo(defaultValue = false.toString())
    val isCompletedToday: Boolean
)

fun HabitEntity.asExternalModel() = Habit(
    id = id,
    name = name,
    isCompletedToday = isCompletedToday
)