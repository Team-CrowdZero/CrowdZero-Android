package com.gdg.feature.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme

@Composable
fun CalendarRoute(
    paddingValues: PaddingValues
) {
    CalendarScreen(
        paddingValues = paddingValues
    )
}

@Composable
fun CalendarScreen(
    paddingValues: PaddingValues = PaddingValues()
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .navigationBarsPadding()
            .statusBarsPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calendar Screen"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CrowdZeroAndroidTheme {
        CalendarScreen()
    }
}
