package com.tuanmanh.inmo.core.designsystem.component

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tuanmanh.inmo.core.designsystem.theme.InmoTheme
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePickerDialog(
    modifier: Modifier = Modifier,
    time: LocalTime = LocalTime.now(),
    onDismiss: () -> Unit = {},
    onTimeSelected: (LocalTime) -> Unit = {}
) {
    val timePickerState = rememberTimePickerState(
        initialMinute = time.minute, initialHour = time.hour, is24Hour = true
    )

    DatePickerDialog(modifier = modifier, onDismissRequest = onDismiss, confirmButton = {
        TextButton(onClick = {
            onTimeSelected(LocalTime.of(timePickerState.hour, timePickerState.minute))
            onDismiss()
        }) {
            Text("OK")
        }
    }, dismissButton = {
        TextButton(onClick = onDismiss) {
            Text("Cancel")
        }
    }) {
        TimePicker(state = timePickerState)
    }
}

@Preview
@Composable
private fun CustomTimePickerDialogPreview() {
    InmoTheme {
        CustomTimePickerDialog()
    }

}