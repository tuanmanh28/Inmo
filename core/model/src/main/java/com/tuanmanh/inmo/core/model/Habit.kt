package com.tuanmanh.inmo.core.model

import java.time.LocalDate

data class Habit(
    val id: Long,
    val name: String,
    val isCompleted: Boolean = false,
    val streak: Int = 0,
    val lastCompletedDate: LocalDate? = null
)