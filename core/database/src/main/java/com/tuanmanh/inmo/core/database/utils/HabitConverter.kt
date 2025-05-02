package com.tuanmanh.inmo.core.database.utils

import androidx.room.TypeConverter
import com.tuanmanh.inmo.core.model.HabitFrequency
import com.tuanmanh.inmo.core.model.HabitType
import com.tuanmanh.inmo.core.model.HabitStatus

class HabitConverter {

    // HabitFrequency Converters
    @TypeConverter
    fun fromHabitFrequency(frequency: HabitFrequency): String {
        return frequency.name
    }

    @TypeConverter
    fun toHabitFrequency(frequency: String): HabitFrequency {
        return HabitFrequency.valueOf(frequency)
    }

    // HabitType Converters
    @TypeConverter
    fun fromHabitType(type: HabitType): String {
        return type.name
    }

    @TypeConverter
    fun toHabitType(type: String): HabitType {
        return HabitType.valueOf(type)
    }

    // HabitStatus Converters
    @TypeConverter
    fun fromHabitStatus(status: HabitStatus): String {
        return status.name
    }

    @TypeConverter
    fun toHabitStatus(status: String): HabitStatus {
        return HabitStatus.valueOf(status)
    }
}