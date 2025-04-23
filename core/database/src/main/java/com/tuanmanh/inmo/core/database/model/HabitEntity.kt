package com.tuanmanh.inmo.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tuanmanh.inmo.core.model.Habit
import java.time.LocalDate

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val isCompleted: Boolean = false,
    val streak: Int = 0,
    val lastCompletedDate: LocalDate? = null
) {
    fun toModel() = Habit(
        id = id,
        name = name,
        isCompleted = isCompleted,
        streak = streak,
        lastCompletedDate = lastCompletedDate
    )
}