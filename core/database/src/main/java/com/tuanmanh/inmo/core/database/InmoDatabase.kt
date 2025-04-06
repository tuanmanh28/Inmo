package com.tuanmanh.inmo.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tuanmanh.inmo.core.database.dao.HabitDao
import com.tuanmanh.inmo.core.database.model.HabitEntity

@Database(
    entities = [
        HabitEntity::class
    ],
    version = 1,
    exportSchema = true
)
internal abstract class InmoDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}