package com.tuanmanh.inmo.features.habittracker.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tuanmanh.inmo.features.habittracker.R

@Composable
fun DeleteHabitDialog(
    habitName: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.delete_habit)) },
        text = { Text(stringResource(R.string.delete_habit_confirmation, habitName)) },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                }
            ) {
                Text(stringResource(android.R.string.ok))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(stringResource(android.R.string.cancel))
            }
        }
    )
} 