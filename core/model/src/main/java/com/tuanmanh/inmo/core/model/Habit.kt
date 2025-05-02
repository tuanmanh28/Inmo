package com.tuanmanh.inmo.core.model

import java.time.LocalDate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.tuanmanh.inmo.core.designsystem.icon.InmoIcons
import java.time.LocalTime

data class Habit(
    val id: Long = 0,
    val name: String = "",
    val description: String? = null,
    val habitFrequency: HabitFrequency = HabitFrequency.DAILY,
    val type: HabitType = HabitType.OTHER,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val reminderTime: LocalTime? = null,
)

enum class HabitFrequency {
    DAILY/*, WEEKLY, MONTHLY*/
}


enum class HabitType(val icon: ImageVector, val color: Color) {
    HEALTH(InmoIcons.Health, Color(0xFF4CAF50)),
    FINANCE(InmoIcons.Finance, Color(0xFFFF9800)),
    EDUCATION(InmoIcons.Education, Color(0xFF2196F3)),
    WORK(InmoIcons.Work, Color(0xFF3F51B5)),
    RELATIONSHIP(InmoIcons.Relationship, Color(0xFFE91E63)),
    PERSONAL_DEVELOPMENT(InmoIcons.PersonalDevelopment, Color(0xFF9C27B0)),
    NUTRITION(InmoIcons.Nutrition, Color(0xFF8BC34A)),
    WORK_OUT(InmoIcons.Workout, Color(0xFFF44336)),
    MINDSET(InmoIcons.Mindset, Color(0xFFFFEB3B)),
    ENTERTAINMENT(InmoIcons.Entertainment, Color(0xFF9575CD)),
    OTHER(InmoIcons.Other, Color(0xFF9E9E9E))
}