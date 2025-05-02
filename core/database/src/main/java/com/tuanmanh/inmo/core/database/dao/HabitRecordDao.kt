package com.tuanmanh.inmo.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tuanmanh.inmo.core.database.model.HabitRecordEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface HabitRecordDao {
    @Insert
    suspend fun insertHabitRecord(record: HabitRecordEntity)

    @Update
    suspend fun updateHabitRecord(record: HabitRecordEntity)

    @Delete
    suspend fun deleteHabitRecord(record: HabitRecordEntity)

    @Query("SELECT * FROM habit_records WHERE date = :date")
    fun getHabitRecordsForDay(date: LocalDate): Flow<List<HabitRecordEntity>>

    @Query("SELECT * FROM habit_records WHERE habitId = :habitId AND date BETWEEN :startDate AND :endDate")
    suspend fun getHabitRecordsForDays(
        habitId: Long,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<HabitRecordEntity>

    @Query("SELECT * FROM habit_records WHERE habitId = :habitId AND date = :date")
    suspend fun getHabitRecord(habitId: Long, date: LocalDate): HabitRecordEntity?
}