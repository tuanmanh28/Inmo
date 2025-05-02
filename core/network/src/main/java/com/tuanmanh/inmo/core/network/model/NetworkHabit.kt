package com.tuanmanh.inmo.core.network.model

import android.annotation.SuppressLint
import com.tuanmanh.inmo.core.model.Habit
import com.tuanmanh.inmo.core.model.HabitFrequency
import com.tuanmanh.inmo.core.model.HabitType
import com.tuanmanh.inmo.core.network.utils.HabitFrequencySerializer
import com.tuanmanh.inmo.core.network.utils.HabitTypeSerializer
import com.tuanmanh.inmo.core.network.utils.LocalDateSerializer
import com.tuanmanh.inmo.core.network.utils.LocalTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalTime

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class NetworkHabit(
    val id: Long,
    val name: String,
    val description: String ?= null,
    @Serializable(with = HabitFrequencySerializer::class) val habitFrequency: HabitFrequency,
    @Serializable(with = HabitTypeSerializer::class) val type: HabitType,
    @Serializable(with = LocalDateSerializer::class) val startDate: LocalDate? = null,
    @Serializable(with = LocalDateSerializer::class) val endDate: LocalDate? = null,
    @Serializable(with = LocalTimeSerializer::class) val reminderTime: LocalTime? = null,
)

fun NetworkHabit.asExternalModel() = Habit(
    id = id,
    name = name,
    description = description,
    habitFrequency = habitFrequency,
    type = type,
    startDate = startDate,
    endDate = endDate,
    reminderTime = reminderTime,
)