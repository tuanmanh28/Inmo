package com.tuanmanh.inmo.core.data.repository.impl

import android.util.Log
import com.tuanmanh.inmo.core.data.model.asEntity
import com.tuanmanh.inmo.core.data.repository.HabitRecordRepository
import com.tuanmanh.inmo.core.database.dao.HabitRecordDao
import com.tuanmanh.inmo.core.model.HabitRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class HabitRecordRepositoryImpl @Inject constructor(
    private val habitRecordDao: HabitRecordDao
): HabitRecordRepository {
    override suspend fun insertHabitRecord(habitRecord: HabitRecord) {
        Log.d(TAG, "insertHabitRecord: $habitRecord")
        habitRecordDao.insertHabitRecord(habitRecord.asEntity())
    }

    override suspend fun updateHabitRecord(habitRecord: HabitRecord) {
        Log.d(TAG, "updateHabitRecord: $habitRecord")
        habitRecordDao.updateHabitRecord(habitRecord.asEntity())
    }

    override suspend fun deleteHabitRecord(habitRecord: HabitRecord) {
        Log.d(TAG, "deleteHabitRecord: $habitRecord")
        habitRecordDao.deleteHabitRecord(habitRecord.asEntity())
    }
    override fun getHabitRecordsForDay(date: LocalDate): Flow<List<HabitRecord>> {
        val habitRecords = habitRecordDao.getHabitRecordsForDay(date).map { entities ->
            entities.map { it.toModel() }
        }
        return habitRecords
    }
    override suspend fun getHabitRecordsForDays(
        habitId: Long,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<HabitRecord> {
        Log.d(TAG, "getHabitRecordsForDays: $habitId, $startDate, $endDate")
        return habitRecordDao.getHabitRecordsForDays(habitId, startDate, endDate)
            .map { it.toModel() }
    }

    override suspend fun getHabitRecord(habitId: Long, localDate: LocalDate): HabitRecord? {
        Log.d(TAG, "getHabitRecord: $habitId, $localDate")
        return habitRecordDao.getHabitRecord(habitId, localDate)?.toModel()
    }
    companion object{
        const val TAG = "HabitRecordRepository"
    }
}