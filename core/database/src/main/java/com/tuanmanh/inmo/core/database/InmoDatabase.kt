package com.tuanmanh.inmo.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tuanmanh.inmo.core.database.dao.HabitDao
import com.tuanmanh.inmo.core.database.model.HabitEntity
import com.tuanmanh.inmo.core.database.utils.DateConverter

@Database(
    entities = [HabitEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class InmoDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}