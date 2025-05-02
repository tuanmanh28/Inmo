package com.tuanmanh.inmo.features.habittracker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.tuanmanh.inmo.core.model.Habit
import com.tuanmanh.inmo.core.model.HabitStatus
import kotlin.math.roundToInt

@Composable
fun HabitItem(
    habitStatus: HabitStatus,
    habit: Habit,
    onToggle: (Long) -> Unit,
    onSkip: (Long) -> Unit,
    onEdit: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val maxSwipe = with(LocalDensity.current) { 160.dp.toPx() }
    var offsetX by remember { mutableFloatStateOf(0f) }

    Box {
        Row(
            modifier = modifier
                .fillMaxSize()
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { onEdit(habit.id) }) {
                Text("Edit", color = Color.Blue)
            }
            TextButton(onClick = { onSkip(habit.id) }) {
                Text("Skip", color = Color.Red)
            }
        }
        // FOREGROUND swipeable content
        Box(modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(onDragEnd = {
                    // Snap to maxSwipe or 0
                    offsetX = if (offsetX < -maxSwipe / 2) -maxSwipe else 0f
                }, onHorizontalDrag = { change, dragAmount ->
                    val newOffset = (offsetX + dragAmount).coerceIn(-maxSwipe, 0f)
                    offsetX = newOffset
                    change.consume()
                })
            }
            .padding(start = 16.dp), contentAlignment = Alignment.CenterStart) {
            Text(
                text = habit.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterStart)
            )
            Checkbox(
                checked = habitStatus == HabitStatus.COMPLETED,
                onCheckedChange = { onToggle(habit.id) },
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
} 