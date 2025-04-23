package com.tuanmanh.inmo.core.network.model

import com.tuanmanh.inmo.core.common.utils.LocalDateSerializer
import com.tuanmanh.inmo.core.model.Habit
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class NetworkHabit(
    val id: Long,
    val name: String,
    val isCompleted: Boolean = false,
    val streak: Int = 0,
    @Serializable(with = LocalDateSerializer::class)
    val lastCompletedDate: LocalDate? = null
)

fun NetworkHabit.asExternalModel() = Habit(
    id = id,
    name = name,
    isCompleted = isCompleted,
    streak = streak,
    lastCompletedDate = lastCompletedDate
)