package com.tuanmanh.inmo.core.designsystem.component

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tuanmanh.inmo.core.designsystem.theme.InmoTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    modifier: Modifier = Modifier,
    date: LocalDate = LocalDate.now(),
    onDismiss: () -> Unit = {},
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date.atStartOfDay(ZoneId.systemDefault()).toInstant()
            .toEpochMilli(), initialDisplayMode = DisplayMode.Picker
    )

    DatePickerDialog(modifier = modifier, onDismissRequest = onDismiss, confirmButton = {
        TextButton(onClick = {
            datePickerState.selectedDateMillis?.let {
                val selectedDate =
                    Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                onDateSelected(selectedDate)
            }
            onDismiss()
        }) {
            Text("OK")
        }
    }, dismissButton = {
        TextButton(onClick = onDismiss) {
            Text("Cancel")
        }
    }) {
        DatePicker(state = datePickerState)
    }
}

@Preview
@Composable
private fun CustomDatePickerDialogPreview() {
    InmoTheme {
        CustomDatePickerDialog()
    }

}