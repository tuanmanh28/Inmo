package com.tuanmanh.inmo.core.network.model

import com.tuanmanh.inmo.core.model.HabitStatus
import com.tuanmanh.inmo.core.network.utils.HabitStatusSerializer
import com.tuanmanh.inmo.core.network.utils.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class NetWorkHabitRecord(
    val id: Long,
    val habitId: Long,
    @Serializable(LocalDateSerializer::class) val date: LocalDate,
    @Serializable(HabitStatusSerializer::class) val status: HabitStatus,
)