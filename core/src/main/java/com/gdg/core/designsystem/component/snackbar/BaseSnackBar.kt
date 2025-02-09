package com.gdg.core.designsystem.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme

@Composable
fun BaseSnackBar(
    message: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                color = CrowdZeroTheme.colors.white,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                vertical = 12.5.dp,
                horizontal = 20.5.dp
            )
    ) {
        Text(
            text = message,
            style = CrowdZeroTheme.typography.c2Medium1,
            color = CrowdZeroTheme.colors.gray800
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BaseSnackBarPreview() {
    CrowdZeroAndroidTheme {
        BaseSnackBar(message = "한 번 더 누르면 앱이 종료됩니다")
    }
}
