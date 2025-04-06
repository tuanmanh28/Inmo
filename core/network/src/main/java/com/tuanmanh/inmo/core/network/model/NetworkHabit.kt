package com.tuanmanh.inmo.core.network.model

import com.tuanmanh.inmo.core.model.Habit
import kotlinx.serialization.Serializable

@Serializable
data class NetworkHabit(
    val id: String,
    val name: String = "",
    val isCompletedToday: Boolean = false
)

fun NetworkHabit.asExternalModel() = Habit(
    id = id,
    name = name,
    isCompletedToday = isCompletedToday
)