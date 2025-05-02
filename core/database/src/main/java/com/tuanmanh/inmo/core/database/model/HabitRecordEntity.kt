package com.tuanmanh.inmo.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tuanmanh.inmo.core.model.HabitRecord
import com.tuanmanh.inmo.core.model.HabitStatus
import java.time.LocalDate

@Entity(tableName = "habit_records")
data class HabitRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val habitId: Long,
    val date: LocalDate,
    val status: HabitStatus
) {
    fun toModel() = HabitRecord(
        id = id,
        habitId = habitId,
        date = date,
        status = status
    )
}