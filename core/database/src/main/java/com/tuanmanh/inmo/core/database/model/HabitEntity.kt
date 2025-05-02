package com.tuanmanh.inmo.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tuanmanh.inmo.core.model.Habit
import com.tuanmanh.inmo.core.model.HabitFrequency
import com.tuanmanh.inmo.core.model.HabitType
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val description: String? = null,
    val type: HabitType,
    val habitFrequency: HabitFrequency,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val reminderTime: LocalTime? = null
) {
    fun toModel() = Habit(
        id = id,
        name = name,
        description = description,
        type = type,
        habitFrequency = habitFrequency,
        startDate = startDate,
        endDate = endDate,
        reminderTime = reminderTime
    )
}