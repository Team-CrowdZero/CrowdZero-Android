package com.gdg.core.designsystem.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme

@Composable
fun BaseSnackBar(
    message: String = ""
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(color = Color.LightGray)
            .padding(
                vertical = 12.dp,
                horizontal = 20.dp
            )
    ) {
        Text(
            text = message
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BaseSnackBarPreview() {
    CrowdZeroAndroidTheme {
        BaseSnackBar()
    }
}
