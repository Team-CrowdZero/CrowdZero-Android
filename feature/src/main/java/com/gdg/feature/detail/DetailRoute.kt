package com.gdg.feature.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme

@Composable
fun DetailRoute(
    id: Int,
    navigateUp: () -> Unit
) {
    DetailScreen()
}

@Composable
fun DetailScreen() {

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    CrowdZeroAndroidTheme {
        DetailScreen()
    }
}
