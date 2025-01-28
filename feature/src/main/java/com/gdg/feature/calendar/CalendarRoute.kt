package com.gdg.feature.calendar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme

@Composable
fun CalendarRoute(
    paddingValues: PaddingValues
) {
    CalendarScreen()
}

@Composable
fun CalendarScreen() {

}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CrowdZeroAndroidTheme {
        CalendarScreen()
    }
}
