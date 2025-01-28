package com.gdg.feature.map

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme

@Composable
fun MapRoute(
    paddingValues: PaddingValues,
    navigateToDetail: (Int) -> Unit
) {
}

@Composable
fun MapScreen(

) {

}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    CrowdZeroAndroidTheme {
        MapScreen()
    }
}