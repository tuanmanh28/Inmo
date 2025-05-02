package com.tuanmanh.inmo.core.model

import java.time.LocalDate

data class HabitRecord(
    val id: Long = 0,
    val habitId: Long,
    val date: LocalDate,
    val status: HabitStatus // refer to HabitStatus enum class
)

enum class HabitStatus {
    COMPLETED, NOT_COMPLETED, SKIPPED
}