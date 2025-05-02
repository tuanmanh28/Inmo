package com.tuanmanh.inmo.core.data.repository

import com.tuanmanh.inmo.core.model.HabitRecord
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HabitRecordRepository {
    suspend fun insertHabitRecord(habitRecord: HabitRecord)
    suspend fun updateHabitRecord(habitRecord: HabitRecord)
    suspend fun deleteHabitRecord(habitRecord: HabitRecord)
    fun getHabitRecordsForDay(date: LocalDate): Flow<List<HabitRecord>>
    suspend fun getHabitRecordsForDays(habitId: Long, startDate: LocalDate, endDate: LocalDate): List<HabitRecord>
    suspend fun getHabitRecord(habitId: Long, localDate: LocalDate): HabitRecord?
}