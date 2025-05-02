package com.tuanmanh.inmo.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tuanmanh.inmo.core.database.dao.HabitDao
import com.tuanmanh.inmo.core.database.dao.HabitRecordDao
import com.tuanmanh.inmo.core.database.model.HabitEntity
import com.tuanmanh.inmo.core.database.model.HabitRecordEntity
import com.tuanmanh.inmo.core.database.utils.DateConverter
import com.tuanmanh.inmo.core.database.utils.HabitConverter
import com.tuanmanh.inmo.core.database.utils.TimeConverter

@Database(
    entities = [HabitEntity::class, HabitRecordEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class, TimeConverter::class, HabitConverter::class)
abstract class InmoDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun habitRecordDao(): HabitRecordDao
}