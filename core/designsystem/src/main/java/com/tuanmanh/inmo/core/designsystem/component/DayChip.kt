package com.tuanmanh.inmo.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuanmanh.inmo.core.designsystem.theme.InmoTheme
import com.tuanmanh.inmo.core.designsystem.theme.ThemePreviews
import java.time.LocalDate

@Composable
fun WeekDayChip(
    modifier: Modifier = Modifier,
    date: LocalDate = LocalDate.now(),
    isCurrentDate: Boolean = false,
    isSelected: Boolean = false,
    onDateSelected: (LocalDate) -> Unit = {},
) {
    val width = 38.dp
    Surface(shape = RoundedCornerShape(10.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surface.copy(alpha = 0.05f),
        contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
        modifier = modifier
            .width(width)
            .clickable { onDateSelected(date) }
            .padding(2.dp)) {
        Box {
            Column(
//            modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 3.dp),
                    text = date.dayOfWeek.name.take(3),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 8.sp
                    )
                )
                Surface(
                    modifier = Modifier.width(width),
                    shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface.copy(alpha = 0.15f),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        val day = if (date.dayOfMonth == 1) {
                            date.dayOfMonth.toString() + "/" + date.monthValue.toString()
                        } else {
                            date.dayOfMonth.toString()
                        }

                        Text(
                            text = day, style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                }
            }
            if (isCurrentDate) {
                Spacer(
                    modifier = Modifier
                        .width(15.dp)
                        .height(1.dp)
                        .clip(RoundedCornerShape(topEnd = 1.dp, topStart = 1.dp))
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
fun DayChip(
    modifier: Modifier = Modifier,
    date: LocalDate = LocalDate.now(),
    isSelected: Boolean = false,
    onClick: (LocalDate) -> Unit = {},
) {
    Surface(shape = MaterialTheme.shapes.small,
        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
        modifier = modifier
            .clickable { onClick(date) }
            .padding(4.dp)) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date.dayOfMonth.toString(), style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@ThemePreviews
@Composable
private fun DayChipPreview() {
    InmoTheme {

        DayChip()
    }
}

@ThemePreviews
@Composable
private fun SelectedDayChipPreview() {
    InmoTheme {
        DayChip(
            isSelected = true,
        )
    }
}

@ThemePreviews
@Composable
private fun WeekDayChipPreview() {
    InmoTheme {

        WeekDayChip()
    }
}

@ThemePreviews
@Composable
private fun SelectedWeekDayChipPreview() {
    InmoTheme {
        WeekDayChip(
            isSelected = true, isCurrentDate = true, date = LocalDate.of(2025, 12, 1)
        )
    }
}